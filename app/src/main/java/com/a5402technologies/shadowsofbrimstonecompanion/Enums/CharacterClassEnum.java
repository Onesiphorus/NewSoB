package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import android.support.v4.app.INotificationSideChannel;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;

import java.util.HashMap;
import java.util.Map;

public enum CharacterClassEnum {
    ANY("Any", "Any", 1),
    RANCHER("Rancher", "Rancher", 2),
    DRIFTER("Drifter", "Drifter", 3),
    OUTLAW("Outlaw", "Outlaw", 4),
    JARGONO_NATIVE("Jargono Native", "Jargono Native", 5),
    US_MARSHAL("U.S. Marshal", "U.S. Marshal", 6),
    PREACHER("Preacher", "Nun", 7),
    LAWMAN("Lawman", "Lawman", 8),
    GUNSLINGER("Gunslinger", "Gunslinger", 9),
    GAMBLER("Gambler", "Gambler", 10),
    DARK_STONE_SHAMAN("Dark Stone Shaman", "Dark Stone Shaman", 11),
    BANDIDO("Bandido", "Bandida", 12),
    FRONTIER_DOC("Frontier Doc", "Frontier Doc", 13),
    SALOON_GIRL("Piano Player", "Saloon Girl", 14),
    WANDERING_SAMARAI("Wandering Samarai", "Wandering Samarai", 15),
    INDIAN_SCOUT("Indian Scout", "Indian Scout", 16),
    COWBOY("Cowboy", "Cowgirl", 17);

    private String female;
    private String male;
    private Integer code;

    private static Map<String, CharacterClassEnum> lookupByFemale = new HashMap<>();
    private static Map<String, CharacterClassEnum> lookupByMale = new HashMap<>();
    private static Map<Integer, CharacterClassEnum> lookupByCode = new HashMap<>();

    static {
        for (CharacterClassEnum e : CharacterClassEnum.values()) {
            lookupByFemale.put(e.female, e);
            lookupByMale.put(e.male, e);
            lookupByCode.put(e.code, e);
        }
    }

    CharacterClassEnum(String male, String female, Integer code) {
        this.male = male;
        this.female = female;
        this.code = code;
    }

    public String male() {
        return this.male;
    }
    public String female() {
        return this.female;
    }

    public Integer code() {
        return this.code;
    }

    public CharacterClassEnum getByFemale(String female) {
        return lookupByFemale.get(female);
    }

    public CharacterClassEnum getByMale(String male) {
        return lookupByMale.get(male);
    }

    public CharacterClassEnum getByCode(Integer code) {
        return lookupByCode.get(code);
    }

    public String getMaleByFemale(String female) {
        return getByFemale(female).male;
    }

    public String getFemaleByMale(String male){
        return getByMale(male).female;
    }

    public Integer getCodeByAny(String name) {
        if(null != getByMale(name).code) return getByMale(name).code;
        else return getByFemale(name).code;
    }
}
