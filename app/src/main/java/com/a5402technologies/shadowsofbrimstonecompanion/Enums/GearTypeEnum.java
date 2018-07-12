package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum GearTypeEnum {
    HAND_WEAPONS("Hand Weapons"),
    RANGED_WEAPONS("Ranged Weapons"),
    CLOTHING("Clothing"),
    GEAR("Gear"),
    GEAR_UPGRADES("Gear Upgrades");

    private static Map<String, GearTypeEnum> lookupByLabel = new HashMap<>();

    static {
        for (GearTypeEnum e : GearTypeEnum.values()) {
            lookupByLabel.put(e.label, e);
        }
    }

    private String label;

    GearTypeEnum(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    public GearTypeEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }
}
