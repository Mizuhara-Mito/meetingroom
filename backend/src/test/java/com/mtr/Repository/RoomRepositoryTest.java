package com.mtr.Repository;

import com.mtr.domain.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

/*    @Autowired
    RoomRepository roomRepository;

    @Test
    public void testRetrieveRooms(){
        roomRepository.save(new Room("A"));
        Room room = roomRepository.findAll().get(0);
        assertThat(room.getName()).isEqualTo("A");
    }*/
}
