package com.gnstudy.parking_management.adapter.in;

import com.gnstudy.parking_management.adapter.in.dto.ParkingControlResultDTO;
import com.gnstudy.parking_management.adapter.in.dto.VehicleEntrtyRequestDTO;
import com.gnstudy.parking_management.adapter.in.dto.VehicleExitRequestDTO;
import com.gnstudy.parking_management.application.port.in.SearchParkingInfoCommand;
import com.gnstudy.parking_management.application.port.in.SearchParkingInfoUseCase;
import com.gnstudy.parking_management.domain.ParkingInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class SearchParkingInfoController {

  private final SearchParkingInfoUseCase searchParkingInfoUseCase;
  @GetMapping("/search")
  @Operation(
      summary = "통합 검색 기능",
      description = "여러 쿼리 형태를 제공하며 통합적으로 검색하는 기능 입니다."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "검색 결과",
          content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ParkingInfo.class))))
  })
  public ResponseEntity<List<ParkingInfo>> searchVehicles(
      @Parameter(description = "주차 여부")
      @RequestParam(value = "isParked", required = false) Boolean isParked,
      @Parameter(description = "검색할 시간범위 시작(~부터)")
      @RequestParam(value = "startDate", required = false) String startDate,
      @Parameter(description = "검색할 시간범위 끝(~까지)")
      @RequestParam(value = "endDate", required = false) String endDate,
      @Parameter(description = "최대 검색 개수")
      @RequestParam(value = "limit", required = false) Integer limit,
      @Parameter(description = "검색할 차량 번호판")
      @RequestParam(value = "plate", required = false) String plate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime startLocalDateTime=null;
    LocalDateTime endLocalDateTime=null;
    if(startDate!=null)
      startLocalDateTime=LocalDateTime.parse(startDate, formatter);
    if(endDate!=null)
      endLocalDateTime=LocalDateTime.parse(endDate, formatter);
    SearchParkingInfoCommand cmd=new SearchParkingInfoCommand(
        null, isParked,startLocalDateTime,endLocalDateTime,limit,plate);
    return ResponseEntity.ok(searchParkingInfoUseCase.findParkingInfos(cmd));
  }

  @GetMapping("/id/{id}")
  @Operation(
      summary = "데이터 ID 검색 기능",
      description = "데이터 ID로 주차/출차 여부를 검색합니다."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "검색 결과 (1건씩 나옴)",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingInfo.class)))
  })
  public ResponseEntity<ParkingInfo> getVehicleById(
      @Parameter(description = "검색할 데이터 ID")
      @PathVariable("id") Long id) {
    return ResponseEntity.ok(searchParkingInfoUseCase.findParkingInfo(SearchParkingInfoCommand.builder().id(id).build()));
  }

  @GetMapping("/plate/{numberplate}")
  @Operation(
      summary = "차량 번호판 검색 기능",
      description = "차량의 번호판으로 주차/출차 여부를 검색합니다."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "검색 결과 (1건씩 나옴)",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingInfo.class)))
  })
  public ResponseEntity<ParkingInfo> getVehicleByPlate(
      @Parameter(description = "검색할 차량 번호판")
      @PathVariable("numberplate") String numberplate) {
    return ResponseEntity.ok(searchParkingInfoUseCase.findParkingInfo(SearchParkingInfoCommand.builder().numberPlate(numberplate).build()));
  }

}
