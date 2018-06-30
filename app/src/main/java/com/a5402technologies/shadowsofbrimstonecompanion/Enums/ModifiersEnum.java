package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum ModifiersEnum {
    AGILITY("Agility", CharacterClassEnum.ANY),
    CUNNING("Cunning", CharacterClassEnum.ANY),
    SPIRIT("Spirit", CharacterClassEnum.ANY),
    STRENGTH("Strength", CharacterClassEnum.ANY),
    LORE("Lore", CharacterClassEnum.ANY),
    LUCK("Luck", CharacterClassEnum.ANY),
    MAX_HEALTH("Max Health", CharacterClassEnum.ANY),
    MAX_SANITY("Max Sanity", CharacterClassEnum.ANY),
    INITIATIVE("Initiative", CharacterClassEnum.ANY),
    MAX_GRIT("Max Grit", CharacterClassEnum.ANY),
    FAITH("Faith", CharacterClassEnum.PREACHER),
    MAGIK("Magik", CharacterClassEnum.DARK_STONE_SHAMAN),
    MAX_FURY("Max Fury", CharacterClassEnum.WANDERING_SAMARAI),
    MOVE("Move", CharacterClassEnum.ANY),
    COMBAT("Combat", CharacterClassEnum.ANY);
    //TODO finish modifiers


    private static Map<CharacterClassEnum, ModifiersEnum> lookupByCode = new HashMap<>();
    private static Map<String, ModifiersEnum> lookupByLabel = new HashMap<>();

    static {
        for (ModifiersEnum e : ModifiersEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    private CharacterClassEnum code;
    private String label;

    ModifiersEnum(String label, CharacterClassEnum code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }

    public CharacterClassEnum code() {
        return this.code;
    }

    public ModifiersEnum getByCode(CharacterClassEnum code) {
        return lookupByCode.get(code);
    }

    public ModifiersEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(CharacterClassEnum code) {
        return getByCode(code).label;
    }

    public CharacterClassEnum getCodeByLabel(String label) {
        return getByLabel(label).code;
    }

}
