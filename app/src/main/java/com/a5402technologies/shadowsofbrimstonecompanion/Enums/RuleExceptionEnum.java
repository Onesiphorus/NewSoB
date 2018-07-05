package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum RuleExceptionEnum {

    DARK_STONE_GRIP("Dark Stone Grip"),
    DARK_STONE_BARREL("Dark Stone Barrel"),
    TRUSTY_PISTOL("Trusty Pistol"),
    TOOLS_OF_SCIENCE("Tools of Science"),
    DUELISTS_GUNBELT("Duelist's Gunbelt");
    private static Map<String, RuleExceptionEnum> lookupByLabel = new HashMap<>();

    static {
        for(RuleExceptionEnum e : RuleExceptionEnum.values()) {
            lookupByLabel.put(e.label, e);
        }
    }

    private String label;

    RuleExceptionEnum(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    public RuleExceptionEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }
}
