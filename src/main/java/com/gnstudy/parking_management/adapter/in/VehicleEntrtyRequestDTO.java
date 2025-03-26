package com.gnstudy.parking_management.adapter.in;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleEntrtyRequestDTO {
  private String plate;
  private LocalDateTime entryTime;

}
