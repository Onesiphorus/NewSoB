package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum ClothingSlotsEnum {

    FACE("Face", "FACE"),
    HAT("Hat", "HAT"),
    SHOULDERS("Shoulders", "SHOULDERS"),
    COAT("Coat", "COAT"),
    TORSO("Torso", "TORSO"),
    GLOVES("Gloves", "GLOVES"),
    BELT("Belt", "BELT"),
    PANTS("Pants", "PANTS"),
    BOOTS("Boots", "BOOTS");


    //TODO finish modifiers


    private String code;
    private String label;

    private static Map<String, ClothingSlotsEnum> lookupByCode = new HashMap<>();
    private static Map<String, ClothingSlotsEnum> lookupByLabel = new HashMap<>();

    static {
        for (ClothingSlotsEnum e : ClothingSlotsEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    ClothingSlotsEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }
    public String code() {
        return this.code;
    }

    public ClothingSlotsEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public ClothingSlotsEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label){
        return getByLabel(label).code;
    }

}
