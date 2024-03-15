package com.nexflare.blog.enums;

public enum BlogType {
    TRAVEL("travel"),FOOD("food"), SHOPPING("shopping");

    BlogType(String val) {
        this.val = val;
    }

    private String val;
}
