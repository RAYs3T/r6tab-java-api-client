package com.gitlab.siegeinsights.r6tab.api.entity;

public enum R6TabRank {

    COPPER_4("copper 4", 1),
    COPPER_3("copper 3", 2),
    COPPER_2("copper 2", 3),
    COPPER_1("copper 1", 4),

    BRONZE_4("bronze 4", 5),
    BRONZE_3("bronze 3", 6),
    BRONZE_2("bronze 2", 7),
    BRONZE_1("bronze 1", 8),

    SILVER_4("silver 4", 9),
    SILVER_3("silver 3", 10),
    SILVER_2("silver 2", 11),
    SILVER_1("silver 1", 12),

    GOLD_4("gold 4", 13),
    GOLD_3("gold 3", 14),
    GOLD_2("gold 2", 15),
    GOLD_1("gold 1", 16),

    PLATINUM_3("platinum 3", 17),
    PLATINUM_2("platinum 2", 18),
    PLATINUM_1("platinum 1", 19),

    DIAMOND("diamond 1", 20);


    private String name;
    private int rankId;

    R6TabRank(String name, int rankId) {
        this.name = name;
        this.rankId = rankId;
    }

    public static R6TabRank getRankById(int rankId) {
        for (R6TabRank current : R6TabRank.values()) {
            if (current.rankId == rankId) {
                return current;
            }
        }
        throw new IllegalArgumentException("No matching rank found for id: " + rankId);
    }

    public static R6TabRank getRankByName(String name) {
        for (R6TabRank current : R6TabRank.values()) {
            if (current.name.equals(name)) {
                return current;
            }
        }
        throw new IllegalArgumentException("No matching rank found for name: " + name);
    }

    public String getName() {
        return name;
    }

    public int getRankId() {
        return rankId;
    }

    @Override
    public String toString() {
        return "Name: " + name + " ID: " + rankId;
    }
}

