package com.nexflare.testhiber.enums;

public enum UserType {
    ADMIN("admin"),GENERAL("general");

    UserType(String val) {
        this.val = val;
    }

    private String val;
}
