package com.gitlab.siegeinsights.r6tab.api.entity.player;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Social implements Serializable {

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
    private String aliases;

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

    public Integer getAliases() {
        if (aliases.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(this.aliases);
    }

    public String getEmbed() {
        return embed;
    }
}
