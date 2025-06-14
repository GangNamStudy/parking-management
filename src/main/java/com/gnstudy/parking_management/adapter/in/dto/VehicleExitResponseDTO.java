package com.gnstudy.parking_management.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class VehicleExitResponseDTO {
  @Schema(description = "주차 상태", example = "true")
  private String status;
  @Schema(description = "번호판", example = "123가4567")
  private String plate;
  @Schema(description = "입차시간", example = "2025-02-15T14:00:0")
  private LocalDateTime entryTime;
  @Schema(description = "출차시간", example = "2025-02-15T16:00:0")
  private LocalDateTime exitTime;
}
