package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum SetListEnum {
    CITY_OF_THE_ANCIENTS("City of the Ancients", "CoA"),
    SWAMPS_OF_DEATH("Swamps of Death", "SoD"),
    CAVERNS_OF_CYNDER("Caverns of Cynder", "CC"),
    TREDERRA("Trederra", "TR"),
    DERELICT_SHIP("Derelict Ship", "DS"),
    LOST_ARMY("Lost Army", "LA"),
    FERAL_VAMPIRES("Feral Vampires", "FV"),
    WEREWOLVES_DEN("Werewolves Den", "WW"),
    CRIMSON_HAND("Cult of the Crimson Hand", "CH"),
    HELLFIRE_SUCCUBUS("Hellfire Succubus", "HS");
    //TODO finish setLists


    private String code;
    private String label;

    private static Map<String, SetListEnum> lookupByCode = new HashMap<>();
    private static Map<String, SetListEnum> lookupByLabel = new HashMap<>();

    static {
        for (SetListEnum e : SetListEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    SetListEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }
    public String code() {
        return this.code;
    }

    public SetListEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public SetListEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label){
        return getByLabel(label).code;
    }
}
