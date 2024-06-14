package com.bgv.domain.theater.service;

import com.bgv.global.openapi.TheaterOpenApi;
import com.bgv.global.openapi.dto.TheaterResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterService {
  private final TheaterOpenApi theaterOpenApi;

  public List<TheaterResponseDto> getTheaterApi(String city) {
    return theaterOpenApi.searchTheaters(city);
  }
}
