package com.gitlab.siegeinsights.r6tab.api.entity;

import com.google.gson.annotations.SerializedName;

public class Social {

    @SerializedName("twitter")
    private String twitter;

    @SerializedName("instagram")
    private String instagram;

    @SerializedName("twitch")
    private String twitch;

    @SerializedName("youtube")
    private String youtube;

    @SerializedName("bio")
    private String bio;

    @SerializedName("esl")
    private String esl;

    @SerializedName("discord")
    private String discord;

    @SerializedName("aliases")
    private int aliases;

    @SerializedName("embed")
    private String embed;

    public String getTwitter() {
        return twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getTwitch() {
        return twitch;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getBio() {
        return bio;
    }

    public String getEsl() {
        return esl;
    }

    public String getDiscord() {
        return discord;
    }

    public int getAliases() {
        return aliases;
    }

    public String getEmbed() {
        return embed;
    }
}
