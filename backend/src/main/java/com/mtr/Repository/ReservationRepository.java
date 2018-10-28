package com.mtr.Repository;

import com.mtr.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    public List<Reservation> findByReservDtOrderBySttTimeAsc(Date reservDt);
    public List<Reservation> findByReservDtAndRoomIdAndSttTimeLessThanAndSttTimeGreaterThan(Date reservDt, int roomId, String endTime, String sttTime);
    public List<Reservation> findByReservDtAndRoomIdAndEndTimeLessThanAndEndTimeGreaterThan(Date reservDt, int roomId, String endTime, String sttTime);
}
