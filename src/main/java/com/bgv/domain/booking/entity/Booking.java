package com.bgv.domain.booking.entity;

import com.bgv.domain.movie.entity.Movie;
import com.bgv.domain.theater.entity.Theater;
import com.bgv.domain.user.entity.User;
import com.bgv.global.common.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Booking extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id")
  private Movie movie;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "theater_id")
  private Theater theater;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  @Column(nullable = false)
  private String ticketNm;
  @Column(nullable = false)
  private String seatNm;
  @Column(nullable = false)
  private int personCnt;

}
