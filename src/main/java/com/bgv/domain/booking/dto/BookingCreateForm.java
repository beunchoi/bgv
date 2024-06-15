package com.bgv.domain.booking.dto;

import lombok.Getter;

@Getter
public class BookingCreateForm {
  private String seatNb;
  private int personCnt;
  private String reserveTime;
  private String movieNm;
  private String theaterNm;
}
