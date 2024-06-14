package com.bgv.global.openapi;

import com.bgv.global.openapi.dto.TheaterResponseDto;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class TheaterOpenApi {

  private final RestTemplate restTemplate;

  public TheaterOpenApi(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  public List<TheaterResponseDto> searchTheaters(String city) {
    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
        .fromUriString("https://openapi.gg.go.kr")
        .path("/MovieTheater")
        .queryParam("KEY", "2c31345bfa60434ab3eb12815e3ac71f")
        .queryParam("Type", "json")
        .queryParam("pIndex",1)
        .queryParam("SIGUN_NM", city)
        .queryParam("BSN_STATE_NM", "운영중","영업중")
        .encode()
        .build()
        .toUri();
    log.info("uri = " + uri);

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .build();

    ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

    log.info("Theater API Status Code : " + responseEntity.getStatusCode());
    log.info("Response Body: " + responseEntity.getBody());

    return fromJSONtoTheater(responseEntity.getBody());
  }

  public List<TheaterResponseDto> fromJSONtoTheater(String responseEntity) {
    JSONObject jsonObject = new JSONObject(responseEntity);
    JSONArray MovieTheater = jsonObject.getJSONArray("MovieTheater");
    JSONObject movieTheaterObject = MovieTheater.getJSONObject(1); // 두 번째 객체가 실제 데이터를 가지고 있음
    JSONArray theaters  = movieTheaterObject.getJSONArray("row");
    List<TheaterResponseDto> theaterDtoList = new ArrayList<>();

    for (Object theater : theaters) {
      TheaterResponseDto theaterDto = new TheaterResponseDto((JSONObject) theater);
      theaterDtoList.add(theaterDto);
    }

    return theaterDtoList;
  }
}