package com.noveria.absencemanagement.view.navigation;

/**
 * Created by lynseymcgregor on 01/07/2015.
 */

public enum NavigationOutcome {
    SUCCESS("success"),FAIL("fail");

    private final String outcomeName;

    NavigationOutcome(String outcomeName) {
        this.outcomeName = outcomeName;
    }

    public String getOutcomeName() {
        return outcomeName;
    }
}
