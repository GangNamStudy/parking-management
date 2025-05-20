package com.gnstudy.parking_management.application.service;

import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoJpaEntity;
import com.gnstudy.parking_management.adapter.out.persistence.ParkingInfoMapper;
import com.gnstudy.parking_management.application.port.in.SearchParkingInfoCommand;
import com.gnstudy.parking_management.application.port.out.SearchParkingInfoPort;
import com.gnstudy.parking_management.domain.ParkingInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchParkingInfoServiceTest {

    @Mock
    private SearchParkingInfoPort searchParkingInfoPort;
    @Mock
    private ParkingInfoMapper parkingInfoMapper;
    @InjectMocks
    private SearchParkingInfoService service;
    private final long SOME_ID = 1L;
    private final String SOME_PLATE = "AB-1234";
    private final LocalDateTime START = LocalDateTime.of(2025,05,20,12,30,0);
    private final LocalDateTime END   = LocalDateTime.of(2025,05,20,14,30,0);

    private ParkingInfoJpaEntity jpaEntity;
    private ParkingInfo domainEntity;

    @BeforeEach
    void setUp() {
        // 공통으로 사용할 더미 JPA/도메인 객체 생성
        jpaEntity    = new ParkingInfoJpaEntity();
        domainEntity = ParkingInfo.generateInfo(
                new ParkingInfo.ParkingInfoID(SOME_ID),
                new ParkingInfo.ParkingInfoPlate(SOME_PLATE),
                new ParkingInfo.ParkingInfoEntryTime(START),
                new ParkingInfo.ParkingInfoExitTime(END),
                new ParkingInfo.ParkingInfoIsParked(true));

    }
    @Test
    @DisplayName("아이디로 주차 정보를 찾는다")
    void findParkingInfo_byId() {
        // given
        given(searchParkingInfoPort.findParkingInfoByID(new ParkingInfo.ParkingInfoID(SOME_ID)))
                .willReturn(jpaEntity);
        given(parkingInfoMapper.mapToDomainEntity(jpaEntity))
                .willReturn(domainEntity);
        var cmd = SearchParkingInfoCommand.builder()
                .id(SOME_ID)
                .build();
        // when
        var result = service.findParkingInfo(cmd);

        // then
        assertThat(result).isSameAs(domainEntity);
    }
    @Test
    @DisplayName("번호판으로 주차 정보를 찾는다")
    void findParkingInfo_byNumberPlate() {
        // given
        given(searchParkingInfoPort.findParkingInfoByPlate(new ParkingInfo.ParkingInfoPlate(SOME_PLATE)))
                .willReturn(jpaEntity);
        given(parkingInfoMapper.mapToDomainEntity(jpaEntity))
                .willReturn(domainEntity);
        var cmd = SearchParkingInfoCommand.builder()
                .numberPlate(SOME_PLATE)
                .build();
        // when
        var result = service.findParkingInfo(cmd);

        // then
        assertThat(result).isSameAs(domainEntity);
    }

}