package com.a5402technologies.shadowsofbrimstonecompanion.Enums;


import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;

import java.util.HashMap;
import java.util.Map;

public enum SkillTypeEnum {

    BLESSING_SERMON("Blessing Sermon", CharacterClassEnum.PREACHER),
    GAMBLING_TRICK("Gambling Trick", CharacterClassEnum.GAMBLER),
    SHOOTIN("Shootin'", CharacterClassEnum.OUTLAW),
    STARTING("Starting", CharacterClassEnum.ANY);
    //TODO finish setLists


    private CharacterClassEnum code;
    private String label;

    private static Map<CharacterClassEnum, SkillTypeEnum> lookupByCode = new HashMap<>();
    private static Map<String, SkillTypeEnum> lookupByLabel = new HashMap<>();

    static {
        for (SkillTypeEnum e : SkillTypeEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    SkillTypeEnum(String label, CharacterClassEnum code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }
    public CharacterClassEnum code() {
        return this.code;
    }

    public SkillTypeEnum getByCode(CharacterClassEnum code) {
        return lookupByCode.get(code);
    }

    public SkillTypeEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(CharacterClassEnum code) {
        return getByCode(code).label;
    }

    public CharacterClassEnum getCodeByLabel(String label){
        return getByLabel(label).code;
    }
}
