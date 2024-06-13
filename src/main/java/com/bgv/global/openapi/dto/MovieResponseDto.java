package com.bgv.global.openapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class MovieResponseDto {
  private String rank;
  private String movieNm;
  private String openDt;
  private String audiAcc;

  public MovieResponseDto(JSONObject movieJson) {
    this.rank = movieJson.getString("rank");
    this.movieNm = movieJson.getString("movieNm");
    this.openDt = movieJson.getString("openDt");
    this.audiAcc = movieJson.getString("audiAcc");
  }
}
