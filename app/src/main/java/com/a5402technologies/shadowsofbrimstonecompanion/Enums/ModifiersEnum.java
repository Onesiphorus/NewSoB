package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

public enum ModifiersEnum {
    AGILITY("Agility"),
    CUNNING("Cunning"),
    SPIRIT("Spirit"),
    STRENGTH("Strength"),
    LORE("Lore"),
    LUCK("Luck"),
    HEALTH("Health"),
    SANITY("Sanity"),
    INITIATIVE("Initiative");


    private int code;
    private String label;

    ModifiersEnum(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

}
