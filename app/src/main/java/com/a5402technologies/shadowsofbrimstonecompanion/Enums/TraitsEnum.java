package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum TraitsEnum {
    LAW("Law","LAW"),
    HOLY("Holy","HOLY"),
    FRONTIER("Frontier","FRONTIER");
    //TODO Finish Traits

    private String code;
    private String label;

    private static Map<String, TraitsEnum> lookupByCode = new HashMap<>();
    private static Map<String, TraitsEnum> lookupByLabel = new HashMap<>();

    static {
        for (TraitsEnum e : TraitsEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    TraitsEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }
    public String code() {
        return this.code;
    }

    public TraitsEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public TraitsEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label){
        return getByLabel(label).code;
    }

    }
