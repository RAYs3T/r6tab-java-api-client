package com.gitlab.siegeinsights.r6tab.api.entity.player;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Player implements Serializable {


    @SerializedName("playerfound")
    private boolean playerFound;

    @SerializedName("social")
    private Social social;

    @SerializedName("matches")
    private List<Match> matches;

    // TODO ADD graph_NA_mmr_get
    // TODO ADD graph_EU_mmr_get
    // TODO ADD graph_AS_mmr_get
    // TODO ADD graph_casualkds_get
    // TODO ADD graph_rankedkds_get

    @SerializedName("p_id")
    private String playerId;

    @SerializedName("p_name")
    private String name;

    @SerializedName("p_user")
    private String user;

    @SerializedName("p_level")
    private int level;

    @SerializedName("p_pvtrank")
    private int pvtRank;

    // TODO Is this in unixtime? Convert it?
    @SerializedName("utime")
    private String uTime;

    // TODO Test data uses 128. Shouldn't that be more like 1.8 or 0.95...?
    @SerializedName("kd")
    private float kd;

    // TODO Add p_data


    @SerializedName("p_visitors")
    private int visitors;

    @SerializedName("p_currentrank")
    private Integer currentRankId;

    @SerializedName("p_currentmmr")
    private int currentMmr;

    @SerializedName("p_maxrank")
    private Integer maxRankId;

    @SerializedName("p_maxmmr")
    private int maxMmr;

    @SerializedName("p_skillrating")
    private int skillRating;

    // TODO Add thunt

    @SerializedName("p_headshotacc")
    private int headshotAccuraccy;

    // TODO add seasons
    // TODO add more other stuff specified by sample


    public boolean isPlayerFound() {
        return playerFound;
    }

    public Social getSocial() {
        return social;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public String getPlayerIdAsString() {
        return playerId;
    }

    public UUID getPlayerId() {
        if (playerId == null) {
            return null;
        }
        return UUID.fromString(playerId);
    }

    public String getName() {
        return name;
    }

    public String getUserAsString() {
        return user;
    }

    public UUID getUser() {
        if (user == null) {
            return null;
        }
        return UUID.fromString(user);
    }

    public int getLevel() {
        return level;
    }

    public int getPvtRank() {
        return pvtRank;
    }

    public String getuTime() {
        return uTime;
    }

    public float getKd() {
        return kd / 100;
    }

    public int getVisitors() {
        return visitors;
    }

    public Integer getCurrentRankId() {
        return currentRankId;
    }

    public R6TabRank getCurrentRank() {
        return R6TabRank.getRankById(getCurrentRankId());
    }

    public int getCurrentMmr() {
        return currentMmr;
    }

    public Integer getMaxRankId() {
        return maxRankId;
    }

    public R6TabRank getMaxRank() {
        return R6TabRank.getRankById(getMaxRankId());
    }

    public int getMaxMmr() {
        return maxMmr;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public int getHeadshotAccuraccy() {
        return headshotAccuraccy;
    }

    @Override
    public String toString() {
        return "PlayerName: " + getName() + " UUID: " + getPlayerIdAsString() + " Rank: " + getCurrentRank() + " MaxRank: " + getMaxRank();
    }
}
