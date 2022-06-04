package com.tempter.edms_rest_api.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentStatus {


    IN_PROGRESS("IN PROGRESS"),
    SIGNED("SIGNED"),
    APPROVED("APPROVED"),
    SIGNING("SIGNING"),
    REJECTED("REJECTED");

    private String value;

    DocumentStatus(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static DocumentStatus fromValue(String text) {
        for (DocumentStatus b : DocumentStatus.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
