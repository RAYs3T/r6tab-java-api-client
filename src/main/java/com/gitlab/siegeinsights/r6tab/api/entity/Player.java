package com.gitlab.siegeinsights.r6tab.api.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Player {


    @SerializedName("social")
    private Social social;

    @SerializedName("matches")
    private List<Match> matches;


    public Social getSocial() {
        return social;
    }


    public List<Match> getMatches() {
        return matches;
    }
}
