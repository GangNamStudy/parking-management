package com.gnstudy.parking_management.application.port.out;

import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoJpaEntity;
import com.gnstudy.parking_management.domain.ParkingInfo;

public interface ParkingActionPort {
    ParkingInfoJpaEntity createParkingInfo(ParkingInfo.ParkingInfoPlate plate,
                                           ParkingInfo.ParkingInfoEntryTime entryTime,
                                           ParkingInfo.ParkingInfoExitTime exitTime);
    ParkingInfoJpaEntity editParkingInfo(ParkingInfo.ParkingInfoID id,
                                         ParkingInfo.ParkingInfoPlate plate,
                                         ParkingInfo.ParkingInfoEntryTime entryTime,
                                         ParkingInfo.ParkingInfoExitTime exitTime,
                                         ParkingInfo.ParkingInfoIsParked isParked);
}
