package com.mtr.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_ROOM")
public class Room {

    @Id
    @GeneratedValue
    private int id;

    public Room(String name) {
        this.name = name;
    }

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
