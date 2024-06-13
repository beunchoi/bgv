package com.bgv.domain.movie.controller;

import com.bgv.domain.movie.service.MovieService;
import com.bgv.global.openapi.dto.MovieResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {
  private final MovieService movieService;

  @GetMapping
  public List<MovieResponseDto> getMovieApi() {
    return movieService.getMovieApi();
  }
}