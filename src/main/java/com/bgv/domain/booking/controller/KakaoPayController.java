package com.bgv.domain.booking.controller;

import com.bgv.domain.booking.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log
public class KakaoPayController {

  @Setter(onMethod_ = @Autowired)
  private KakaoPayService kakaoPay;

  @GetMapping("/kakaoPay")
  public void kakaoPayGet() {

  }

  @PostMapping("/kakaoPay")
  public String kakaoPay(){
    log.info("kakaoPay post.....................");

    return "redirect:" + kakaoPay.kakaoPayReady();
  }

  @GetMapping("/kakaoPaySuccess")
  public void kakaoPaySuccess(@RequestParam("pg_token")String pg_token, Model model) {
    log.info("kakaoPay Success get................");
    log.info("kakaoPaySuccess pg_token : " + pg_token);
  }
}
