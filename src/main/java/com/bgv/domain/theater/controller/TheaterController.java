package com.bgv.domain.theater.controller;

import com.bgv.domain.theater.service.TheaterService;
import com.bgv.global.openapi.dto.TheaterResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theater")
@RequiredArgsConstructor
public class TheaterController {
  private final TheaterService theaterService;

  @GetMapping
  public List<TheaterResponseDto> getTheaterApi(@RequestParam String city) {
    return theaterService.getTheaterApi(city);
  }
}
