package com.gnstudy.parking_management.adapter.out.persistence;

import com.gnstudy.parking_management.application.port.out.ParkingActionPort;
import com.gnstudy.parking_management.application.port.out.SearchParkingInfoPort;
import com.gnstudy.parking_management.common.PersistenceAdapter;
import com.gnstudy.parking_management.domain.ParkingInfo;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoID;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoIsParked;
import com.gnstudy.parking_management.domain.ParkingInfo.ParkingInfoPlate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class ParkingInfoPersistenceAdapter implements SearchParkingInfoPort, ParkingActionPort {

  private final ParkingInfoRepository repository;
  @Override
  public ParkingInfoJpaEntity findParkingInfoByID(ParkingInfoID id) {
    return repository.findById(id.getId()).orElse(null);
  }

  @Override
  public ParkingInfoJpaEntity findParkingInfoByPlate(ParkingInfoPlate plate) {
    return repository.findByPlateAndParkedTrue(plate.getPlate());
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


  @Override
  public ParkingInfoJpaEntity createParkingInfo(ParkingInfoPlate plate, ParkingInfo.ParkingInfoEntryTime entryTime, ParkingInfo.ParkingInfoExitTime exitTime) {
    ParkingInfoJpaEntity entity=new ParkingInfoJpaEntity(plate.getPlate(),
            entryTime.getEntryTime(),
            exitTime.getExitTime(),
            exitTime.getExitTime()==null);
    return repository.save(entity);
  }

  @Override
  @Transactional
  public ParkingInfoJpaEntity editParkingInfo(ParkingInfoID id, ParkingInfoPlate plate, ParkingInfo.ParkingInfoEntryTime entryTime, ParkingInfo.ParkingInfoExitTime exitTime, ParkingInfoIsParked isParked) {
    ParkingInfoJpaEntity entity = repository.getReferenceById(id.getId());
    if (plate != null)      entity.setPlate(plate.getPlate());
    if (entryTime != null)  entity.setEntryTime(entryTime.getEntryTime());
    if (exitTime != null)   entity.setExitTime(exitTime.getExitTime());
    if (isParked != null)   entity.setParked(isParked.getIsParked());
    return entity;
  }
}
