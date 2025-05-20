package com.gnstudy.parking_management.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_info")
@Data
@NoArgsConstructor
public class ParkingInfoJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String plate;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;
  private boolean parked;

}
