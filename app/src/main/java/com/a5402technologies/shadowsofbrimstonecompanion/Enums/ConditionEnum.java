package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum ConditionEnum {
    INJURY("Injury"),
    MADNESS("Madness"),
    MUTATION("Mutation"),
    OTHER("Other");

    private static Map<String, ConditionEnum> lookupByLabel = new HashMap<>();

    static {
        for (ConditionEnum e : ConditionEnum.values()) {
            lookupByLabel.put(e.label, e);
        }
    }

    private String label;

    ConditionEnum(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    public ConditionEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }
}
