package com.gnstudy.parking_management.adapter.in;

import com.gnstudy.parking_management.adapter.in.dto.ParkingControlResultDTO;
import com.gnstudy.parking_management.adapter.in.dto.VehicleEntrtyRequestDTO;
import com.gnstudy.parking_management.adapter.in.dto.VehicleExitRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class ParkingActionController {
  @PostMapping("/")
  @Operation(
      summary = "차량 입차 기능",
      description = "차량 입차 요청 시 차량 번호판과 optional 입차 시간을 포함한 데이터를 받아 처리합니다. 입차 시간이 전달되지 않으면 현재 시간을 기본값으로 사용합니다."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "차량 입차 성공",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingControlResultDTO.class)))
  })
  public ResponseEntity<ParkingControlResultDTO> vehicleEntry(@RequestBody VehicleEntrtyRequestDTO request) {
    //TODO:서비스 코드 작성
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @PutMapping("/exit/{plate}")
  @Operation(
      summary = "차량 출차 기능",
      description = "차량 출차 요청 시 차량 번호판과 optional 출차 시간을 포함한 데이터를 받아 처리합니다. 출차 시간이 전달되지 않으면 현재 시간을 기본값으로 사용합니다."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "차량 출차 성공",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingControlResultDTO.class)))
  })
  public ResponseEntity<ParkingControlResultDTO> vehicleExit(
      @Parameter(description = "출차할 차량 번호판")
      @PathVariable("plate") String plate,
      @RequestBody(required = false) VehicleExitRequestDTO request) {
    //TODO:서비스 코드 작성
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
