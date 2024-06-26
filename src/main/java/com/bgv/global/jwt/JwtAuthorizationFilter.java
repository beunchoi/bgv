package com.bgv.global.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain filterChain) throws ServletException, IOException {

    String tokenValue = jwtUtil.getJwtFromHeader(req);

    if (StringUtils.hasText(tokenValue)) {
//			// JWT 토큰 substring                                // validate 두 번할 필요 없음, 암복호화는 비싼 로직이라 중복 안하는게 좋음
//			tokenValue = jwtUtil.substringToken(tokenValue);
////			log.info(tokenValue);
//
//			if (!jwtUtil.validateToken(tokenValue)) {
//				log.error("Token Error");
//				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//				res.setCharacterEncoding("utf-8");
//				res.getWriter().write("토큰이 유효하지 않습니다.");
//				return;
//			}
      try {
        Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
        setAuthentication(info.getSubject());
      } catch (Exception e) {
        log.error(e.getMessage());
        return;
      }
    }

    filterChain.doFilter(req, res);
  }

  // 인증 처리
  public void setAuthentication(String username) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    Authentication authentication = createAuthentication(username);
    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
  }

  // 인증 객체 생성
  private Authentication createAuthentication(String username) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(userDetails, null,
        userDetails.getAuthorities());
  }
}
