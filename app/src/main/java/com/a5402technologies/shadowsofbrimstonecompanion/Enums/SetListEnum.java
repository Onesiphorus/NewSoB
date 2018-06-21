package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

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


    private String code;
    private String label;

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
}
