package com.mtr.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_ROOM")
public class Room {

    @Id
    @GeneratedValue
    @Column(name="room_id", length = 10, nullable = false)
    private int roomId;

    public Room(){};

    public Room(String name) {
        this.name = name;
    }

    @Column
    private String name;

    public int getId() {
        return roomId;
    }

    public void setId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomId(){
        return this.roomId;
    }
}
