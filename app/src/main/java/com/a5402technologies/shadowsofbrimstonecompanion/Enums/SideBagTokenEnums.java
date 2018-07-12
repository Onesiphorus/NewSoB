package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum SideBagTokenEnums {
    ANTI_RAD("Anti-Rad"),
    BANDAGES("Bandages"),
    DYNAMITE("Dynamite"),
    EXOTIC_HERBS("Exotic Herbs"),
    FINE_CIGAR("Fine Cigar"),
    FIRE_SAKE("Fire Sake"),
    FLASH("Flash"),
    HATCHET("Hatchet"),
    HERBS("Herbs"),
    HOLY_WATER("Holy Water"),
    JUNK_BOMB("Junk Bomb"),
    LANTERN_OIL("Langern Oil"),
    NECTAR("Nectar"),
    POTION("Potion"),
    SHATTER("Shatter"),
    SPICE("Spice"),
    STAKE("Stake"),
    SWAMP_FUNGUS("Swamp Fungus"),
    TEQUILA("Tequila"),
    TONIC("Tonic"),
    WHISKEY("Whiskey");


    private static Map<String, SideBagTokenEnums> lookupByLabel = new HashMap<>();

    static {
        for (SideBagTokenEnums e : SideBagTokenEnums.values()) {
            lookupByLabel.put(e.label, e);
        }
    }

    private String label;

    SideBagTokenEnums(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    public SideBagTokenEnums getByLabel(String label) {
        return lookupByLabel.get(label);
    }
}
