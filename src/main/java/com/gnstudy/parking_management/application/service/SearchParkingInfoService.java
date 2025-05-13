package com.gnstudy.parking_management.application.service;

import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoJpaEntity;
import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoMapper;
import com.gnstudy.parking_management.application.port.in.SearchParkingInfoCommand;
import com.gnstudy.parking_management.application.port.in.SearchParkingInfoUseCase;
import com.gnstudy.parking_management.application.port.out.SearchParkingInfoPort;
import com.gnstudy.parking_management.common.UseCase;
import com.gnstudy.parking_management.domain.ParkingInfo;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoEntryTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoID;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoIsParked;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoPlate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class SearchParkingInfoService implements SearchParkingInfoUseCase {
  private final SearchParkingInfoPort searchParkingInfoPort;
  private final ParkingInfoMapper parkingInfoMapper;
  @Override
  public ParkingInfo findParkingInfo(SearchParkingInfoCommand command) {
    if(command.getId()!=null)
      return parkingInfoMapper.mapToDomainEntity(
          searchParkingInfoPort.findParkingInfoByID(new ParkingInfoID(command.getId())));
    if(command.getNumberPlate()!=null)
      return parkingInfoMapper.mapToDomainEntity(
          searchParkingInfoPort.findParkingInfoByPlate(new ParkingInfoPlate(command.getNumberPlate())));
    return parkingInfoMapper.mapToDomainEntity(searchParkingInfoPort.findParkingInfos(
        new ParkingInfoIsParked(command.getIsParked()),
        new ParkingInfoPlate(command.getNumberPlate()),
        command.getStartDate(),
        command.getEndDate(),
        1).getFirst());
  }

  @Override
  public List<ParkingInfo> findParkingInfos(SearchParkingInfoCommand command) {
    List<ParkingInfoJpaEntity> jpaResult=new ArrayList<>();
    if(command.getId()!=null)
      jpaResult.add(searchParkingInfoPort.findParkingInfoByID(new ParkingInfoID(command.getId())));
    else if(command.getNumberPlate()!=null)
      jpaResult.add(searchParkingInfoPort.findParkingInfoByPlate(new ParkingInfoPlate(command.getNumberPlate())));
    else
      jpaResult=searchParkingInfoPort.findParkingInfos(
          new ParkingInfoIsParked(command.getIsParked()),
          new ParkingInfoPlate(command.getNumberPlate()),
          command.getStartDate(),
          command.getEndDate(),
          command.getLimit());
    return parkingInfoMapper.mapToDomainEntityList(jpaResult);
  }
}
