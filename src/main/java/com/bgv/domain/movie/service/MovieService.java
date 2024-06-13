package com.bgv.domain.movie.service;

import com.bgv.global.openapi.MovieOpenApi;
import com.bgv.global.openapi.dto.MovieResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
  private final MovieOpenApi movieOpenApi;

  public List<MovieResponseDto> getMovieApi() {
    return movieOpenApi.getApi();
  }
}
