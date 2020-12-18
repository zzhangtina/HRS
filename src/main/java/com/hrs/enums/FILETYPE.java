package com.hrs.enums;

public enum FILETYPE {
    JSON("json"),
    YAML("yml");

    private final String value;

    FILETYPE(String v){
        value = v;
    }

    public String value() {
        return value;
    }

    public static FILETYPE fromValue(String v) {
        for (FILETYPE s: FILETYPE.values()) {
            if (s.value.equals(v)) {
                return s;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
