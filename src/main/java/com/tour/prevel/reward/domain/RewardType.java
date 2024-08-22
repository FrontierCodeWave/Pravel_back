package com.tour.prevel.reward.domain;

public enum RewardType {
    COUPON("COUPON"),
    GIFT("GIFT")
    ;

    private String type;

    RewardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
