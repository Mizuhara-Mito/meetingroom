package com.mtr.service;

public class ReservationRuntimeException extends RuntimeException {

    public ReservationRuntimeException() {
    }
    public ReservationRuntimeException(String message) {
        super(message);
    }

    public ReservationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationRuntimeException(Throwable cause) {
        super(cause);
    }
}
