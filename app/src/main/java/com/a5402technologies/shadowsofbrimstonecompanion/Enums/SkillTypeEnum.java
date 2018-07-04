package com.a5402technologies.shadowsofbrimstonecompanion.Enums;


import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;

import java.util.HashMap;
import java.util.Map;

public enum SkillTypeEnum {

    BLESSING_SERMON("Blessing Sermon", CharacterClassEnum.PREACHER.male()),
    GAMBLING_TRICK("Gambling Trick", CharacterClassEnum.GAMBLER.male()),
    SHOOTIN("Shootin'", CharacterClassEnum.OUTLAW.male()),
    INFAMY("Infamy", CharacterClassEnum.OUTLAW.male()),
    CHARM("Charm", CharacterClassEnum.OUTLAW.male()),
    ON_THE_RUN("On the Run", CharacterClassEnum.OUTLAW.male()),
    ON_THE_RANGE("On the Range", CharacterClassEnum.COWBOY.male()),
    THRILL_SEEKER("Thrill-seeker", CharacterClassEnum.COWBOY.male()),
    FISTICUFFS("Fisticuffs", CharacterClassEnum.COWBOY.male()),
    SHOW_OFF("Show-off", CharacterClassEnum.COWBOY.male()),
    JARGONO("Jargono", CharacterClassEnum.JARGONO_NATIVE.male()),
    GLADIATOR("Gladiator", CharacterClassEnum.JARGONO_NATIVE.male()),
    FEROCITY("Ferocity", CharacterClassEnum.JARGONO_NATIVE.male()),
    HUNTING_AND_TRACKING("Hunting & Tracking", CharacterClassEnum.JARGONO_NATIVE.male()),
    STARTING("Starting", CharacterClassEnum.ANY.male());
    //TODO finish setLists


    private static Map<String, SkillTypeEnum> lookupByCode = new HashMap<>();
    private static Map<String, SkillTypeEnum> lookupByLabel = new HashMap<>();

    static {
        for (SkillTypeEnum e : SkillTypeEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    private String code;
    private String label;

    SkillTypeEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }

    public String code() {
        return this.code;
    }

    public SkillTypeEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public SkillTypeEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label) {
        return getByLabel(label).code;
    }
}
