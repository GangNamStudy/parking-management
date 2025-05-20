package com.gnstudy.parking_management.application.port.in;

import com.gnstudy.parking_management.common.UseCase;
import com.gnstudy.parking_management.domain.ParkingInfo;
import java.util.List;

@UseCase
public interface SearchParkingInfoUseCase {
  ParkingInfo findParkingInfo(SearchParkingInfoCommand command);
  List<ParkingInfo> findParkingInfos(SearchParkingInfoCommand command);
}
