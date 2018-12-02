package com.mtr.Repository;

import com.mtr.domain.Reservation;
import com.mtr.domain.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTest {

   /* @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Test
    public void testRetrieveReservation() throws Exception{
        //given
        Room room = new Room("A");
        roomRepository.save(room);

        List<Reservation> reservations = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("2018-07-01");
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Reservation reservation = new Reservation("1100", sqlStartDate, "1200", "홍길동", room);

        //when
        reservationRepository.save(reservation);

        //then
        Reservation savedReservation = reservationRepository.findAll().get(0);
        assertThat(savedReservation.getRoom().getName()).isEqualTo("A");

    }*/
}
