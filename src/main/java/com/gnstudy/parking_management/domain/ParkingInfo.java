package com.gnstudy.parking_management.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ParkingInfo {
  private Long id;
  private String plate;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;
  private boolean isParked;

  public static ParkingInfo generateInfo(ParkingInfoID id,
      ParkingInfoPlate plate,
      ParkingInfoEntryTime entryTime,
      ParkingInfoExitTime exitTime,
      ParkingInfoIsParked isParked) {
    return new ParkingInfo(
        id.getId(),
        plate.getPlate(),
        entryTime.getEntryTime(),
        exitTime.getEntryTime(),
        isParked.isParked()
    );
  }

  @Value
  public static class ParkingInfoID{
    Long id;
    public ParkingInfoID(Long id){this.id=id;}
  }

  @Value
  public static class ParkingInfoPlate{
    String plate;
    public ParkingInfoPlate(String plate){this.plate=plate;}
  }

  @Value
  public static class ParkingInfoEntryTime{
    LocalDateTime entryTime;
    public ParkingInfoEntryTime(LocalDateTime entryTime){this.entryTime=entryTime;}
  }

  @Value
  public static class ParkingInfoExitTime{
    LocalDateTime entryTime;
    public ParkingInfoExitTime(LocalDateTime entryTime){this.entryTime=entryTime;}
  }

  @Value
  public static class ParkingInfoIsParked{
    boolean isParked;
    public ParkingInfoIsParked(boolean isParked){this.isParked=isParked;}
  }


}
