package com.mtr.domain;

import java.sql.Date;

public class SystemMessage {
    Date reservDate;
    String resultMessage;

    public SystemMessage(Date reservDate, String resultMessage){
        this.reservDate = reservDate;
        this.resultMessage = resultMessage;
    }

    public Date getReservDate() {
        return reservDate;
    }

    public void setReservDate(Date reservDate) {
        this.reservDate = reservDate;
    }

    public String getMessage() {
        return resultMessage;
    }

    public void setMessage(String message) {
        resultMessage = message;
    }
}
