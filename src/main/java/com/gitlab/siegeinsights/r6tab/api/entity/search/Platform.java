package com.gitlab.siegeinsights.r6tab.api.entity.search;

public enum Platform {
    UPLAY("uplay"),
    PLAYSTATION_NETWORK("psn"),
    XBOX("xbl");

    private String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Platform getPlatformByName(String name) {
        for (Platform platform : Platform.values()) {
            if (platform.getName().equals(name)) {
                return platform;
            }
        }
        throw new IllegalArgumentException("No platform with name: " + name);
    }
}
