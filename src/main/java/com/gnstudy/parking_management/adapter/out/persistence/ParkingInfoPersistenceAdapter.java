package com.gnstudy.parking_management.adapter.out.persistence;

import com.gnstudy.parking_management.application.port.out.SearchParkingInfoPort;
import com.gnstudy.parking_management.common.PersistenceAdapter;
import com.gnstudy.parking_management.domain.ParkingInfo;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoEntryTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoExitTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoID;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoIsParked;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoPlate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class ParkingInfoPersistenceAdapter implements SearchParkingInfoPort {

  private final ParkingInfoRepository repository;
  @Override
  public ParkingInfoJpaEntity findParkingInfoByID(ParkingInfoID id) {
    return repository.findById(id.getId()).orElse(null);
  }

  @Override
  public ParkingInfoJpaEntity findParkingInfoByPlate(ParkingInfoPlate plate) {
    return repository.findByPlate(plate.getPlate());
  }

  @Override
  public List<ParkingInfoJpaEntity> findParkingInfos(
      ParkingInfoIsParked parkingInfoIsParked,
      ParkingInfoPlate parkingInfoPlate,
      LocalDateTime startTime,
      LocalDateTime endTime,
      Integer limit) {
    if(limit==null)
      limit=50;
    Pageable pageable = PageRequest.of(0, limit);
    return repository.search(parkingInfoIsParked.getIsParked(),
        startTime,
        endTime,
        parkingInfoPlate.getPlate(),
        pageable);
  }
}
