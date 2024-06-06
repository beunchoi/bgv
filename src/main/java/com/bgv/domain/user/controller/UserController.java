package com.bgv.domain.user.controller;

import com.bgv.domain.user.dto.LoginRequestDto;
import com.bgv.domain.user.dto.ProfileRequestDto;
import com.bgv.domain.user.dto.ProfileResponseDto;
import com.bgv.domain.user.dto.SignupRequestDto;
import com.bgv.domain.user.service.UserService;
import com.bgv.global.common.ResponseDto;
import com.bgv.global.jwt.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  @PostMapping("/users/signup")
  public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto,
      BindingResult bindingResult) {

    String errorMessages = "";
    if (bindingResult.hasErrors()) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        errorMessages +=
            fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n";
      }
      return ResponseEntity.badRequest().body(ResponseDto.fail(400, errorMessages));
    }

    userService.signup(requestDto);

    return ResponseEntity.ok().body(ResponseDto.success(200));
  }

  @PostMapping("/users/login")
  public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto request,
      HttpServletResponse response) {

    userService.login(request, response);

    return ResponseEntity.ok().body(ResponseDto.success(200));
  }

  @PutMapping("/users/profile")
  public ResponseEntity<ResponseDto> updateProfile(
      @Valid @RequestBody ProfileRequestDto request, BindingResult bindingResult,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    String errorMessages = "";
    if (bindingResult.hasErrors()) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        errorMessages +=
            fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n";
      }
      return ResponseEntity.badRequest().body(ResponseDto.fail(400, errorMessages));
    }

    ProfileResponseDto response = userService.updateProfile(userDetails.getUser(), request);

    return ResponseEntity.ok().body(ResponseDto.success(200, response));
  }

  @DeleteMapping("/users/inactive")
  public ResponseEntity<ResponseDto> inActiveUser(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    userService.inActiveUser(userDetails.getUser());

    return ResponseEntity.ok()
        .body(ResponseDto.success(200));
  }
}
