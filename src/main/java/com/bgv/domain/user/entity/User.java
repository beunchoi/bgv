package com.bgv.domain.user.entity;

import com.bgv.domain.user.dto.ProfileRequestDto;
import com.bgv.domain.user.dto.SignupRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String userInfo = "자기소개 해주세요";

  @Column(nullable = false)
  private boolean active = true;

  public User(SignupRequestDto request, String password) {
    this.username = request.getUsername();
    this.email = request.getEmail();
    this.password = password;
  }

  public void updateProfile(ProfileRequestDto request) {
    this.username = request.getUsername();
    this.email = request.getEmail();
    this.userInfo = request.getUserInfo();
  }

  public void inActiveUser() {
    this.active = false;
  }
}
