package com.bgv.global.openapi;

import com.bgv.global.openapi.dto.MovieResponseDto;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class MovieOpenApi {

  private final RestTemplate restTemplate;

  public MovieOpenApi(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  public List<MovieResponseDto> getApi() {
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formattedDate = date.format(formatter);

    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
        .fromUriString("http://www.kobis.or.kr")
        .path("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
        .queryParam("key", "4e7665b7652f0edab0b200ae7ec79726")
        .queryParam("targetDt", "20240613")
        .encode()
        .build()
        .toUri();
    log.info("uri = " + uri);

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .build();

    ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

    log.info("Movie API Status Code : " + responseEntity.getStatusCode());
    log.info("Response Body: " + responseEntity.getBody());

    return fromJSONtoMovies(responseEntity.getBody());
  }

  public List<MovieResponseDto> fromJSONtoMovies(String responseEntity) {
    JSONObject jsonObject = new JSONObject(responseEntity);
    JSONObject boxOfficeResult = jsonObject.getJSONObject("boxOfficeResult");
    JSONArray boxOfficeArray = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
    List<MovieResponseDto> movieList = new ArrayList<>();

    for (Object movie : boxOfficeArray) {
      MovieResponseDto movieDto = new MovieResponseDto((JSONObject) movie);
      movieList.add(movieDto);
    }

    return movieList;
  }
}