package com.gnstudy.parking_management.adapter.in;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParkingControlResultDTO {
  private String status;
  private String plate;
  private LocalDateTime entryTime;

}
