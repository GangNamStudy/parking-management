package com.gnstudy.parking_management.adapter.in;

import com.gnstudy.parking_management.domain.ParkingInfo;
import java.util.List;
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
public class SearchParkingInfoController {
  @GetMapping("/search")
  public ResponseEntity<List<ParkingInfo>> searchVehicles(
      @RequestParam(value = "isParked", required = false) Boolean isParked,
      @RequestParam(value = "startDate", required = false) String startDate,
      @RequestParam(value = "endDate", required = false) String endDate,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "plate", required = false) String plate) {
    return null;
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ParkingInfo> getVehicleById(@PathVariable("id") Long id) {
    return null;
  }

  @GetMapping("/plate/{numberplate}")
  public ResponseEntity<ParkingInfo> getVehicleByPlate(@PathVariable("numberplate") String numberplate) {
    // 실제 조회 로직은 생략

    return null;
  }

  @PostMapping("/")
  public ResponseEntity<ParkingControlResultDTO> vehicleEntry(@RequestBody VehicleEntrtyRequestDTO request) {

    return null;
  }

  @PutMapping("/exit/{plate}")
  public ResponseEntity<ParkingControlResultDTO> vehicleExit(
      @PathVariable("plate") String plate,
      @RequestBody(required = false) VehicleExitRequestDTO request) {
    return null;
  }




}
