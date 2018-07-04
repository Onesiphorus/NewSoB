package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum ShopEnum {

    NONE("None", "NON"),
    DOCS_OFFICE("Doc's Office", "DOC"),
    GENERAL_STORE("General Store", "GEN"),
    BLACKSMITH("Blacksmith", "BLK"),
    CHURCH("Church", "CH"),
    FRONTIER_OUTPOST("Frontier Outpost", "FRN"),
    SALOON("Saloon", "SAL"),
    SHERIFFS_OFFICE("Sheriff's Office", "SHF"),
    SMUGGLERS_DEN("Smuggler's Den", "SMD"),
    MUTANT_QUARTER("Mutant Quarter", "MUTANT QUARTER"),
    GAMBLNG_HALL("Gambling Hall", "GAMBLING HALL"),
    STREET_MARKET("Street Market", "STREET MARKET"),
    INDIAN_TRADING_POST("Indian Trading Post", "INDIAN TRADING POST");

    private static Map<String, ShopEnum> lookupByCode = new HashMap<>();
    private static Map<String, ShopEnum> lookupByLabel = new HashMap<>();

    static {
        for (ShopEnum e : ShopEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    private String code;
    private String label;

    ShopEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }

    public String code() {
        return this.code;
    }

    public ShopEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public ShopEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label) {
        return getByLabel(label).code;
    }
}
