package com.bgv.domain.booking.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoPayDto {
  private String tid; // 결제 고유 번호
  private String next_redirect_app_url;
  private String next_redirect_mobile_url;
  private String next_redirect_pc_url; // web - 받는 결제 페이지
  private String android_app_scheme;
  private String ios_app_scheme;
  private Date created_at;
}
