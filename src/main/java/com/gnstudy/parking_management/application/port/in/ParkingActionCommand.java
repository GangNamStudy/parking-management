package com.gnstudy.parking_management.application.port.in;

import com.gnstudy.parking_management.common.utill.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ParkingActionCommand extends SelfValidating<SearchParkingInfoCommand> {
    @NotNull
    private String plate;
    @NotNull
    private LocalDateTime timeInfo;

    public ParkingActionCommand(String plate,LocalDateTime timeInfo)
    {
        this.plate=plate;
        this.timeInfo=timeInfo;
        this.validateSelf();
    }
}
