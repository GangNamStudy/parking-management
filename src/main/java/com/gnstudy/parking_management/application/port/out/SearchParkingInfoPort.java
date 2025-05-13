package com.gnstudy.parking_management.application.port.out;

import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoJpaEntity;
import com.gnstudy.parking_management.domain.ParkingInfo;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoEntryTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoExitTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoIsParked;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoPlate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchParkingInfoPort {
  ParkingInfoJpaEntity findParkingInfoByID(ParkingInfo.ParkingInfoID id);
  ParkingInfoJpaEntity findParkingInfoByPlate(ParkingInfo.ParkingInfoPlate plate);
  List<ParkingInfoJpaEntity> findParkingInfos(
      ParkingInfoIsParked parkingInfoIsParked,
      ParkingInfoPlate parkingInfoPlate,
      LocalDateTime startTime,
      LocalDateTime endTime,
      Integer limit);

}
