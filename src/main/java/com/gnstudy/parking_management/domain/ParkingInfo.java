package com.gnstudy.parking_management.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ParkingInfo {
  @Schema(description = "데이터 번호", example = "1")
  private Long id;
  @Schema(description = "번호판", example = "123가5678")
  private String plate;
  @Schema(description = "입차 시간", example = "2025-02-15T14:00:0")
  private LocalDateTime entryTime;
  @Schema(description = "출차 시간", example = "2025-03-15T14:00:0")
  private LocalDateTime exitTime;
  @Schema(description = "주차 상태", example = "true")
  private boolean parked;

  public static ParkingInfo generateInfo(ParkingInfoID id,
      ParkingInfoPlate plate,
      ParkingInfoEntryTime entryTime,
      ParkingInfoExitTime exitTime,
      ParkingInfoIsParked isParked) {
    if(isParked.isParked==null)
      throw new RuntimeException();
    return new ParkingInfo(
        id.getId(),
        plate.getPlate(),
        entryTime.getEntryTime(),
        exitTime.getExitTime(),
        isParked.getIsParked()
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
    LocalDateTime exitTime;
    public ParkingInfoExitTime(LocalDateTime exitTime){this.exitTime=exitTime;}
  }

  @Value
  public static class ParkingInfoIsParked{
    Boolean isParked;
    public ParkingInfoIsParked(Boolean isParked){this.isParked=isParked;}
  }

  public void exit(LocalDateTime exitTime)
  {
    if(this.parked==false || this.exitTime!=null)
      throw new RuntimeException("이미 출차된 차량 입니다.");
    this.parked=false;
    this.exitTime=exitTime;
  }


}
