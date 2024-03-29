package com.github.faening.eng_soft_fp_api.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class ExceptionResponse implements Serializable {
    String timestamp;
    String messasge;
    String details;

    public ExceptionResponse(String timestamp, String messasge, String details) {
        this.timestamp = timestamp;
        this.messasge = messasge;
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessasge() {
        return messasge;
    }

    public String getDetails() {
        return details;
    }
}