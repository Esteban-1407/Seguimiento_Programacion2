package model;

import java.util.Arrays;

public enum ToyType {
    FEMALE("Female"),
    MALE("Male"),
    UNISEX("Unisex");
    private final String name;

    ToyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static ToyType fromName(String name) {
        return Arrays.stream(ToyType.values()).filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    }

