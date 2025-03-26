package com.gnstudy.parking_management.adapter.in;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleExitRequestDTO {
  private LocalDateTime exitTime;
}
