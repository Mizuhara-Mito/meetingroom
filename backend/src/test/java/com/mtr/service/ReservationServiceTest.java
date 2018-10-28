package com.mtr.service;

import com.mtr.Repository.ReservationRepository;
import com.mtr.Repository.RoomRepository;
import com.mtr.domain.Reservation;
import com.mtr.domain.Room;
import com.mtr.domain.SystemMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationService reservationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository, roomRepository);
    }

    @Test
    public void doReservationTest() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("2018-07-01");
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Reservation reservation = new Reservation("1100", sqlStartDate, "12:00", "홍길동", 1);
        List<SystemMessage> messageList = new ArrayList<>();
        messageList.add(new SystemMessage(sqlStartDate,"예약에 성공하였습니다"));
        given(reservationRepository.save(reservation)).willReturn(reservation);

        //when
        final SystemMessage reservation1 = (SystemMessage) reservationService.reserve(reservation).get(0);

        //then
        assertThat(reservationService.reserve(reservation)).isNotNull();
        assertThat(reservation1.getReservDate()).isEqualTo(sqlStartDate);
    }

    @Test
    public void retrieveRoomsTest() throws Exception {
        //given
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("A"));
        roomList.add(new Room("B"));
        given(roomRepository.findAll()).willReturn(roomList);

        //when
        final Room room1 = (Room)reservationService.retrieveRooms().get(0);
        final Room room2 = (Room)reservationService.retrieveRooms().get(1);

        //then
        assertThat(room1.getName()).isEqualTo("A");
        assertThat(room2.getName()).isEqualTo("B");
    }

    @Test
    public void retrieveReserveInfo() throws Exception {
        //given
        List<Reservation> reservations = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("2018-07-01");
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Reservation reservation = new Reservation("1100", sqlStartDate, "12:00", "홍길동", 1);
        reservations.add(reservation);
        given(reservationRepository.findByReservDtOrderBySttTimeAsc(sqlStartDate)).willReturn(reservations);

        //when
        final Reservation reservation1 = (Reservation)reservationService.retrieveReservation("2018-07-01").get(0);

        //then
        assertThat(reservation1).isNotNull();
        assertThat(reservation1.getUserName()).isEqualTo("홍길동");
    }

}
