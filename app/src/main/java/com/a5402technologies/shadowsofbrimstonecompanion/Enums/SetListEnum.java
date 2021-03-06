package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum SetListEnum {
    BASE("Base", "BASE"),
    TARGA("Targa", "CoA"),
    JARGONO("Jargono", "SoD"),
    CAVERNS_OF_CYNDER("Caverns of Cynder", "CC"),
    TREDERRA("Trederra", "TR"),
    DERELICT_SHIP("Derelict Ship", "DS"),
    LOST_ARMY("Lost Army", "LA"),
    FERAL_VAMPIRES("Feral Vampires", "FV"),
    BLACK_FANG_TRIBE("Black Fang Tribe", "BLACK FANG TRIBE"),
    WEREWOLVES_DEN("Werewolves Den", "WW"),
    CRIMSON_HAND("Cult of the Crimson Hand", "CH"),
    HELLFIRE_SUCCUBUS("Hellfire Succubus", "HS"),
    SCAFFORD_GANG("Scafford Gang", "SG"),
    UNDEAD_OUTLAWS("Undead Outlaws", "UO"),
    ALLIES_OF_THE_OLD_WEST("Allies of the Old West", "AL"),
    DOORWAYS_INTO_DARKNESS("Doorways Into Darkness", "DD"),
    PROMO("Promo", "P"),
    JARGONO_NATIVE("Jargono Native", "HCJN"),
    COWBOY("Cowboy", "HCC"),
    MASTERS_OF_THE_VOID("Masters of the Void", "MV"),
    SERPENTMEN_OF_JARGONO("Serpentmen of Jargono", "SJ"),
    HERO_CLASS("Hero Class", "HC"),
    FRONTIER_TOWN("Frontier Town", "FT");


    private static Map<String, SetListEnum> lookupByCode = new HashMap<>();
    private static Map<String, SetListEnum> lookupByLabel = new HashMap<>();

    static {
        for (SetListEnum e : SetListEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

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

    public SetListEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public SetListEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label) {
        return getByLabel(label).code;
    }
}
