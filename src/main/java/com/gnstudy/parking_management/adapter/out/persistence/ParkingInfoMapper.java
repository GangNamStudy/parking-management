package com.gnstudy.parking_management.adapter.out.persistence;

import com.gnstudy.parking_management.domain.ParkingInfo;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoEntryTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoExitTime;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoID;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoIsParked;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoPlate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ParkingInfoMapper {
  public ParkingInfo mapToDomainEntity(ParkingInfoJpaEntity entity){
    if(entity==null)
      return null;
    return ParkingInfo.generateInfo(
        new ParkingInfoID(entity.getId()),
        new ParkingInfoPlate(entity.getPlate()),
        new ParkingInfoEntryTime(entity.getEntryTime()),
        new ParkingInfoExitTime(entity.getExitTime()),
        new ParkingInfoIsParked(entity.isParked())
    );
  }
  public List<ParkingInfo> mapToDomainEntityList(List<ParkingInfoJpaEntity> entity){
    List<ParkingInfo>result=new ArrayList<>();
    for(ParkingInfoJpaEntity jpaEntity : entity){
      ParkingInfo temp=mapToDomainEntity(jpaEntity);
      if(temp!=null)
        result.add(temp);
    }
    return result;
  }
}
