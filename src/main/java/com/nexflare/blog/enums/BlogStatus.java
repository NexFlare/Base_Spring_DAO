package com.nexflare.blog.enums;

public enum BlogStatus {

    PENDING("pending"), APPROVED("approved"), REJECTED("rejected");

    BlogStatus(String val) {
        this.val = val;
    }

    private String val;
}
