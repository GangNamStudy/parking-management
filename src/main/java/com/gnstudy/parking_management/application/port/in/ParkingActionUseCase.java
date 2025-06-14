package com.gnstudy.parking_management.application.port.in;


import com.gnstudy.parking_management.domain.ParkingInfo;

public interface ParkingActionUseCase {
    ParkingInfo entry(ParkingActionCommand command);
    ParkingInfo exit(ParkingActionCommand command);
}
