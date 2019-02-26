package com.gitlab.siegeinsights.r6tab.api.entity.search;

import com.gitlab.siegeinsights.r6tab.api.entity.player.R6TabRank;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("p_id")
    private String uuid;


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

    @SerializedName("kd")
    private Integer kd;

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public String getPlatformString() {
        return platform;
    }

    public Platform getPlatform() {
        if (platform == null) {
            return null;
        }
        return Platform.getPlatformByName(platform);
    }

    public String getUserUuid() {
        return userUuid;
    }

    public Integer getCurrentMmr() {
        return currentMmr;
    }

    public R6TabRank getCurrentRank() {
        if (currentRank == null || currentRank == 0) {
            return null;
        }
        return R6TabRank.getRankById(currentRank);
    }

    public Float getKd() {
        // Kd is specified as 100 for 1.0
        // We want a representative float
        if (kd == null || kd == 0) {
            return null;
        }
        return (float) kd / 100;
    }
}
