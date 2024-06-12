package com.bgv.global.openapi;

import com.bgv.global.openapi.dto.MovieResponseDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class MovieOpenApi {

  private final RestTemplate restTemplate;
  @Value("${movie_apikey}")
  private String movie_apikey;

  public MovieResponseDto getAPI(String search) {
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    String formattedDate = date.format(formatter);

    String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?" +
        "key=" + movie_apikey
        + "&&targetDt=" + formattedDate;

    ResponseEntity<MovieResponseDto> response  = restTemplate.exchange(apiURL, HttpMethod.GET, null, MovieResponseDto.class);
    return response.getBody();
  }

}
