package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

public enum RestrictionEnum {
    LAW("Law"),
    HOLY("Holy"),
    FRONTIER("Frontier");

    private int code;
    private String label;

    RestrictionEnum(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    }
