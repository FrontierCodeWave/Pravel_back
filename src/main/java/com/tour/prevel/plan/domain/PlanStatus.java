package com.tour.prevel.plan.domain;

public enum PlanStatus {

    REVISION("REVISION"),
    PROGRESS("PROGRESS"),
    COMPLETE("COMPLETE"),
    CANCEL("CANCEL"),
    EXPIRED("EXPIRED")
    ;

    private String status;

    PlanStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
