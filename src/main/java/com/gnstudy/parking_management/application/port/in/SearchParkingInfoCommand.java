package com.gnstudy.parking_management.application.port.in;

import com.gnstudy.parking_management.common.utill.SelfValidating;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SearchParkingInfoCommand extends SelfValidating<SearchParkingInfoCommand> {

  private final Long id;
  private final Boolean isParked;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;
  private final Integer limit;
  private final String numberPlate;

  public SearchParkingInfoCommand(Long id, Boolean isParked, LocalDateTime startDate, LocalDateTime endDate,
      Integer limit, String numberPlate) {
    this.id = id;
    this.isParked = isParked;
    this.startDate = startDate;
    this.endDate = endDate;
    this.limit = limit;
    this.numberPlate = numberPlate;
    this.validateSelf();
  }



}
