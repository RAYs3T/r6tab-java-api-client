package com.gitlab.siegeinsights.r6tab.api.entity.leaderboard;

import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

public class LeaderBoardEntry implements Serializable {
    @SerializedName("position")
    private int position;

    @SerializedName("p_id")
    private String playerUuid;

    @SerializedName("p_name")
    private String name;

    @SerializedName("p_level")
    private Integer level;

    @SerializedName("p_platform")
    private String platform;

    @SerializedName("p_user")
    private String userUuid;

    @SerializedName("p_currentmmr")
    private Integer currentMmr;

    @SerializedName("p_currentrank")
    private Integer currentRank;

    @SerializedName("p_skillrating")
    private Integer skillRating;

    @SerializedName("p_NA_rank")
    private Integer naRank;

    @SerializedName("p_EU_rank")
    private Integer euRank;

    @SerializedName("p_AS_rank")
    private Integer asRank;

    @SerializedName("kd")
    private Integer kd;

    @SerializedName("p_headshotacc")
    private Integer headshotAccuracy;


    public int getPosition() {
        return position;
    }

    public String getPlayerUuidRaw() {
        return playerUuid;
    }

    public UUID getPlayerUuid() {
        if (playerUuid == null) {
            return null;
        }
        return UUID.fromString(playerUuid);
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public String getPlatformRaw() {
        return platform;
    }

    public Platform getPlatform() {
        return Platform.getPlatformByName(platform);
    }

    public String getUserUuidRaw() {
        return userUuid;
    }

    public UUID getUserUuid() {
        if (userUuid == null) {
            return null;
        }
        return UUID.fromString(userUuid);
    }

    public Integer getCurrentMmr() {
        return currentMmr;
    }

    public Integer getCurrentRank() {
        return currentRank;
    }

    public Integer getSkillRating() {
        return skillRating;
    }

    public Integer getNaRank() {
        return naRank;
    }

    public Integer getEuRank() {
        return euRank;
    }

    public Integer getAsRank() {
        return asRank;
    }

    public Float getKd() {
        if (kd == null) {
            return null;
        }
        return (float) kd / 100;
    }

    public Integer getHeadshotAccuracy() {
        return headshotAccuracy;
    }
}
