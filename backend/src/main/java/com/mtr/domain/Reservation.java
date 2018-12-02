package com.mtr.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "TB_RESERVATION")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="reserv_id", length = 10)
    int reservId;

    @ManyToOne
    @JoinColumn(name="room_id", insertable = false)
    private Room room;

    @Column(name="stt_time", length = 4)
    String sttTime;

    @Column(name="reserv_dt", length = 10)
    Date reservDt;

    @Column(name="end_time", length = 4)
    String endTime;

    @Column(name="user_name")
    String userName;

    @Column(name="repeat_num", length = 2)
    int repeatNum;

    public Reservation(String sttTime, Date reservDt, String endTime, String userName, Room room) {
        this.sttTime = sttTime;
        this.reservDt = reservDt;
        this.endTime = endTime;
        this.userName = userName;
        this.room = room;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public Room getRoom(){
        return this.room;
    }

    public Reservation(){};

    public int getReservId() {
        return reservId;
    }

    public void setReservId(int reservId) {
        this.reservId = reservId;
    }

    public String getSttTime() {
        return sttTime;
    }

    public void setSttTime(String sttTime) {
        this.sttTime = sttTime;
    }

    public Date getReservDt() {
        return reservDt;
    }

    public void setReservDt(Date reservDt) {
        this.reservDt = reservDt;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRepeatNum() {
        return repeatNum;
    }

    public void setRepeatNum(int repeatNum) {
        this.repeatNum = repeatNum;
    }
}
