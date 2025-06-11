package com.gnstudy.parking_management.application.service;

import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoJpaEntity;
import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoMapper;
import com.gnstudy.parking_management.application.port.in.ParkingActionCommand;
import com.gnstudy.parking_management.application.port.in.ParkingActionUseCase;
import com.gnstudy.parking_management.application.port.out.ParkingActionPort;
import com.gnstudy.parking_management.application.port.out.SearchParkingInfoPort;
import com.gnstudy.parking_management.common.UseCase;
import com.gnstudy.parking_management.domain.ParkingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class ParkingActionService implements ParkingActionUseCase {

    private final SearchParkingInfoPort searchParkingInfoPort;
    private final ParkingActionPort parkingActionPort;
    private final ParkingInfoMapper parkingInfoMapper;
    @Override
    public ParkingInfo entry(ParkingActionCommand command) {
        return parkingInfoMapper.mapToDomainEntity(parkingActionPort.createParkingInfo(new ParkingInfo.ParkingInfoPlate(command.getPlate()),
                new ParkingInfo.ParkingInfoEntryTime(command.getTimeInfo()),
                new ParkingInfo.ParkingInfoExitTime(null)));
    }

    @Override
    public ParkingInfo exit(ParkingActionCommand command) {
        ParkingInfoJpaEntity entity = searchParkingInfoPort.findParkingInfoByPlate(new ParkingInfo.ParkingInfoPlate(command.getPlate()));
        if(entity == null)
            throw new RuntimeException("들어온 적이 없는 차량 입니다.");
        ParkingInfo parkingInfo=parkingInfoMapper.mapToDomainEntity(entity);
        parkingInfo.exit(command.getTimeInfo());
        return parkingInfoMapper.mapToDomainEntity(parkingActionPort.editParkingInfo(new ParkingInfo.ParkingInfoID(parkingInfo.getId()),
                new ParkingInfo.ParkingInfoPlate(parkingInfo.getPlate()),
                new ParkingInfo.ParkingInfoEntryTime(parkingInfo.getEntryTime()),
                new ParkingInfo.ParkingInfoExitTime(parkingInfo.getExitTime()),
                new ParkingInfo.ParkingInfoIsParked(parkingInfo.isParked())));
    }
}
