package com.gnstudy.parking_management.adapter.out.persistence;

import com.gnstudy.parking_management.domain.ParkingInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
public interface ParkingInfoRepository extends JpaRepository<ParkingInfoJpaEntity,Long>{
  @Query("""
        SELECT p
          FROM ParkingInfoJpaEntity p
         WHERE (:isParked  IS NULL OR p.parked   = :isParked)
           AND (:startDate IS NULL OR p.entryTime >= :startDate)
           AND (:endDate   IS NULL OR p.exitTime  <= :endDate)
           AND (:plate     IS NULL OR p.plate    LIKE CONCAT('%', :plate, '%'))
         ORDER BY p.entryTime DESC
        """)
  List<ParkingInfoJpaEntity> search(
      @Param("isParked")  Boolean isParked,
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate")   LocalDateTime endDate,
      @Param("plate")     String plate,
      Pageable pageable
  );
  ParkingInfoJpaEntity findByPlateAndParkedTrue(String plate);
}
