package com.bgv.global.openapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class TheaterResponseDto {
  private String BIZPLC_NM;
  private String REFINE_ROADNM_ADDR;
  private String SIGUN_NM;
  private String FIRST_REGIST_PNT_TM;

  public TheaterResponseDto(JSONObject theaterJson) {
    this.BIZPLC_NM = theaterJson.getString("BIZPLC_NM");
    this.REFINE_ROADNM_ADDR = theaterJson.isNull("REFINE_ROADNM_ADDR") ? null : theaterJson.getString("REFINE_ROADNM_ADDR");
    this.SIGUN_NM = theaterJson.getString("SIGUN_NM");
    this.FIRST_REGIST_PNT_TM = theaterJson.getString("FIRST_REGIST_PNT_TM");
  }
}
