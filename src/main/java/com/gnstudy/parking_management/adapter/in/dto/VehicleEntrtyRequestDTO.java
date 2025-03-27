package com.gnstudy.parking_management.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleEntrtyRequestDTO {
  @Schema(description = "번호판", example = "123가5678")
  private String plate;
  @Schema(description = "입차 시간", example = "2025-02-15T14:00:0")
  private LocalDateTime entryTime;

}
