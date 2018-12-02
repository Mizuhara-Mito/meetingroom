package com.mtr.service;

import com.mtr.Repository.ReservationRepository;
import com.mtr.Repository.RoomRepository;
import com.mtr.domain.Reservation;
import com.mtr.domain.Room;
import com.mtr.domain.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final static int ADD_DAY = 7;
    private final static String DUPLICATED_MSG = "중복 예약입니다.";
    private final static String RESERVE_MSG = "예약에 성공하였습니다";
    private List<SystemMessage> messages;

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * @desc 모든 방 정보 받기
     * @return 방 List
     */
    public List retrieveRooms() throws Exception {
        logger.info("=============================> GET ROOMS ");
        List<Room> roomList = roomRepository.findAll();
        return roomList;
    }

    /**
     * @desc 모든 예약정보 받기
     * @param 기준날짜
     * @return 예약정보 List
     */
    public List<Reservation> retrieveReservation(String reservDt) {

        try {
            logger.info("=============================> RESERVATION DATE : {}", reservDt);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(reservDt);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            return reservationRepository.findByReservDtOrderBySttTimeAsc(sqlStartDate);
        } catch (Exception ex){
            throw new ReservationRuntimeException("날짜 형식이 잘못되었습니다.");
        }
    }

    /**
     *
     * @param 회의실별 예약정보 받기
     * @param reservDt, roomName
     * @return 회의실별 예약정보 List
     */
/*    public List<Reservation> retreiveReservationByRoom(String reservDt, String roomName) {

    }*/

    /**
     * @desc 단건 or 다건(반복) 예약을 하여 예약건별로 성공 실패 메세지를 담은 메세지 객체의 리스트를 리턴함
     * @param 예약정보
     * @return 예약결과 Message 객체 List
     */
    public List<SystemMessage> reserve(Reservation reservation) throws Exception {
        if( reservation.getRepeatNum() > 0 ){
            doReservation(reservation);
            int repeatNum = reservation.getRepeatNum();
            while(repeatNum-- > 0){
               reservation.setReservDt(addDays(reservation.getReservDt()));
               doReservation(reservation);
            }
        } else {
            doReservation(reservation);
        }
        return messages;
    }

    /**
     * @desc 예약 dao 호출
     * @param 예약정보
     */
    public void doReservation(Reservation reservation) throws Exception {
        messages = new ArrayList<SystemMessage>();

        logger.info("=============================> USER NAME : {}", reservation.getUserName());
        logger.info("============================> RESERVE DATE : {}", reservation.getReservDt());
        logger.info("============================> RESERVE ENDTIME : {}", reservation.getEndTime());
        logger.info("============================> RESERVE START TIME : {}", reservation.getSttTime());
        logger.info("============================> RESERVE ROOM : {}", reservation.getRoom());

        Reservation reserv = new Reservation(reservation.getSttTime()
                ,reservation.getReservDt()
                ,reservation.getEndTime()
                ,reservation.getUserName()
                ,reservation.getRoom()
        );

        if(isDuplicatedReservation(reserv)){
            messages.add(new SystemMessage( reserv.getReservDt(), DUPLICATED_MSG ));
        } else {
            reservationRepository.save(reserv);
            messages.add(new SystemMessage( reserv.getReservDt(), RESERVE_MSG ));
        }
    }

    /**
     * @desc 예약 요청건의 시간대에 이미 중복된 예약이 있는지 유무를 리턴
     * @param 예약정보
     * @return 중복예약유무
     */
    public boolean isDuplicatedReservation(Reservation reservation) throws Exception {
        int sttTimeDuplCnt = reservationRepository.findByReservDtAndRoomIdAndSttTimeLessThanAndSttTimeGreaterThan(
                reservation.getReservDt(), reservation.getRoom().getRoomId(), reservation.getEndTime(), reservation.getSttTime() ).size();
        int endTimeDuplCnt = reservationRepository.findByReservDtAndRoomIdAndEndTimeLessThanAndEndTimeGreaterThan(
                reservation.getReservDt(), reservation.getRoom().getRoomId(), reservation.getEndTime(), reservation.getSttTime() ).size();

        if(sttTimeDuplCnt > 0 || endTimeDuplCnt > 0){
            return true;
        }
        return false;
    }

    /**
     * @desc 반복예약을 위해 현재 예약 요청날짜에서 7일 이후의 날짜를 구해 리턴해줌
     * @param 예약요청 날짜
     * @return 7일 이후의 날짜
     */
    public static Date addDays(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, ADD_DAY);
        return new Date(c.getTimeInMillis());
    }

}
