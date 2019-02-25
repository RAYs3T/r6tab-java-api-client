package com.gitlab.siegeinsights.r6tab.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Player {


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
    private int currentRank;

    @SerializedName("p_currentmmr")
    private int currentMmr;

    @SerializedName("p_maxrank")
    private int maxRank;

    @SerializedName("p_maxmmr")
    private int maxMmr;

    @SerializedName("p_skillrating")
    private int skillRating;

    // TODO Add thunt

    @SerializedName("p_headshotacc")
    private int headshotAccuraccy;

    // TODO add seasons
    // TODO add more other stuff specified by sample


    public Social getSocial() {
        return social;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
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
        return kd;
    }

    public int getVisitors() {
        return visitors;
    }

    public int getCurrentRank() {
        return currentRank;
    }

    public int getCurrentMmr() {
        return currentMmr;
    }

    public int getMaxRank() {
        return maxRank;
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
}
