package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum ModifiersEnum {
    AGILITY("Agility","AGI"),
    CUNNING("Cunning","CUN"),
    SPIRIT("Spirit","SPI"),
    STRENGTH("Strength", "STR"),
    LORE("Lore","LOR"),
    LUCK("Luck","LCK"),
    MAX_HEALTH("Max Health","MHP"),
    MAX_SANITY("Max Sanity","MSP"),
    INITIATIVE("Initiative","INI"),
    MAX_GRIT("Max Grit","MG");
    //TODO finish modifiers


    private String code;
    private String label;

    private static Map<String, ModifiersEnum> lookupByCode = new HashMap<>();
    private static Map<String, ModifiersEnum> lookupByLabel = new HashMap<>();

    static {
        for (ModifiersEnum e : ModifiersEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    ModifiersEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }
    public String code() {
        return this.code;
    }

    public ModifiersEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public ModifiersEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label){
        return getByLabel(label).code;
    }

}
