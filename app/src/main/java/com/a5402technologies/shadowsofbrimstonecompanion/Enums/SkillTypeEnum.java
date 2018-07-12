package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum SkillTypeEnum {

    BLESSING_SERMON("Blessing Sermon", CharacterClassEnum.PREACHER.male()),
    GAMBLING_TRICK("Gambling Trick", CharacterClassEnum.GAMBLER.male()),
    OUTLAW_SHOOTIN("Shootin'", CharacterClassEnum.OUTLAW.male()),
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
    LEADERSHIP("Leadership", CharacterClassEnum.LAWMAN.male()),
    DETERMINATION("Determination", CharacterClassEnum.LAWMAN.male()),
    JUSTICE("Justice", CharacterClassEnum.LAWMAN.male()),
    LAW_AND_ORDER("Law and Order", CharacterClassEnum.LAWMAN.male()),
    TRAVELER("Traveler", CharacterClassEnum.US_MARSHAL.male()),
    RESOLVE("Resolve", CharacterClassEnum.US_MARSHAL.male()),
    HONOR("Honor", CharacterClassEnum.US_MARSHAL.male()),
    BOUNTY_HUNTER("Bounty Hunter", CharacterClassEnum.US_MARSHAL.male()),
    FIGHTING("Fighting", CharacterClassEnum.SALOON_GIRL.female()),
    MOXY("Moxy", CharacterClassEnum.SALOON_GIRL.female()),
    ACROBATICS("Acrobatics", CharacterClassEnum.SALOON_GIRL.female()),
    SALOON_CHARM("Charm", CharacterClassEnum.SALOON_GIRL.female()),
    GUNS("Guns", CharacterClassEnum.BANDIDO.male()),
    EXPLOSIVES("Explosives", CharacterClassEnum.BANDIDO.male()),
    BRAWLING("Brawling", CharacterClassEnum.BANDIDO.male()),
    SCOUNDREL("Scoundrel", CharacterClassEnum.BANDIDO.male()),
    SHOOTIN("Shootin'", CharacterClassEnum.GUNSLINGER.male()),
    SHOWMANSHIP("Showmanship", CharacterClassEnum.GUNSLINGER.male()),
    WAY_OF_THE_GUN("Way of the Gun", CharacterClassEnum.GUNSLINGER.male()),
    REPUTATION("Reputation", CharacterClassEnum.GUNSLINGER.male()),
    TRICKS_TRADE("Tricks of the Trade", CharacterClassEnum.GAMBLER.male()),
    ROLL_BONES("Roll the Bones", CharacterClassEnum.GAMBLER.male()),
    CIVILIZED("Civilized", CharacterClassEnum.GAMBLER.male()),
    CARD_SHARK("Card Shark", CharacterClassEnum.GAMBLER.male()),
    MARKSMANSHIP("Marksmanship", CharacterClassEnum.RANCHER.male()),
    FRONTIER_SURVIVAL("Frontier Survival", CharacterClassEnum.RANCHER.male()),
    TOUGHNESS("Touchness", CharacterClassEnum.RANCHER.male()),
    SMITHING("Smithing", CharacterClassEnum.RANCHER.male()),
    TRACKING("Tracking", CharacterClassEnum.INDIAN_SCOUT.male()),
    STEALTH("Stealth", CharacterClassEnum.INDIAN_SCOUT.male()),
    WARRIOR("Warrior", CharacterClassEnum.INDIAN_SCOUT.male()),
    TRIBAL("Tribal", CharacterClassEnum.INDIAN_SCOUT.male()),
    DEVOTION("Devotion", CharacterClassEnum.PREACHER.male()),
    WRATH("Wrath", CharacterClassEnum.PREACHER.male()),
    FANATIC("Fanatic", CharacterClassEnum.PREACHER.male()),
    OCCULT_STUDIES("Occult Studies", CharacterClassEnum.PREACHER.male()),
    HEALING("Healing", CharacterClassEnum.FRONTIER_DOC.male()),
    SCIENCE("Science", CharacterClassEnum.FRONTIER_DOC.male()),
    FIELD_WORK("Field Work", CharacterClassEnum.FRONTIER_DOC.male()),
    RESEARCH("Research", CharacterClassEnum.FRONTIER_DOC.male()),
    SPIRITUAL_WARRIOR("Spiritual Warrior", CharacterClassEnum.DARK_STONE_SHAMAN.male()),
    TRANSFORMATION("Transformation", CharacterClassEnum.DARK_STONE_SHAMAN.male()),
    DARK_STONE_MASTERY("Dark Stone Mastery", CharacterClassEnum.DARK_STONE_SHAMAN.male()),
    TRIBAL_LORE("Tribal Lore", CharacterClassEnum.DARK_STONE_SHAMAN.male()),
    STARTING("Starting", CharacterClassEnum.ANY.male());


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
