package com.olukunle.mkobo_assessment.constants;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    SUCCESS("Success", "MwI00"),
    FAILED("Failed", "MwF00"),
    RECORD_ALREADY_EXIST("A staff already exist with the email address provided", "MwF01"),
    INVALID_REQUEST("Invalid request", "MwE00"),
    INVALID_STAFF_ID("Invalid staffId", "MwE01"),
    GENERAL_ERROR("An error occurred", "MwG00"),
    ENCRYPTION_ERROR("Encryption error", "MwG01"),
    INVALID_FIELDS_PROVIDED("Invalid fields provided", "MwE01"),
    INVALID_AUTHORIZATION("Invalid authorization, access denied", "MwE02");

    private final String status;
    private final String code;

    ResponseStatus(String status, String code) {
        this.status = status;
        this.code = code;
    }

}
