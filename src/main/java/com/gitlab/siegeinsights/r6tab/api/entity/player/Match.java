package com.gitlab.siegeinsights.r6tab.api.entity.player;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable {
    @SerializedName("ranked_wlstatus")
    private String rankedWinLostStatus;

    @SerializedName("ranked_winslost")
    private String rankedWinLost;

    @SerializedName("ranked_datatime")
    private Date rankedDataTime;


    // TODO What is this for?
    @SerializedName("next")
    private String next;


    @SerializedName("db_p_total_casualwins")
    private int dbTotalCasualWins;

    @SerializedName("db_p_total_casuallosses")
    private int dbTotalCasualLosses;

    @SerializedName("db_p_total_casualkills")
    private int dbTotalCasualKills;

    @SerializedName("db_p_total_casualdeaths")
    private int dbTotalCasualDeaths;

    @SerializedName("db_p_total_rankedwins")
    private int dbTotalRankedWins;

    @SerializedName("db_p_total_rankedlosses")
    private int dbTotalRankedLosses;

    @SerializedName("db_p_total_rankedkills")
    private int dbTotalRankedKills;

    @SerializedName("db_p_total_rankeddeaths")
    private int dbTotalRankedDeaths;

    @SerializedName("db_p_total_totalhs")
    private int dbTotalHs;

    @SerializedName("db_p_NA_currentmmr")
    private Integer dbCurrentMmrNa;

    @SerializedName("db_p_EU_currentmmr")
    private Integer dbCurrentMmrEu;

    @SerializedName("db_p_AS_currentmmr")
    private Integer dbCurrentMmrAs;

    @SerializedName("NA_mmrchange")
    private Integer dbMmrChangeNa;

    @SerializedName("EU_mmrchange")
    private Integer dbMmrChangeEu;

    @SerializedName("AS_mmrchange")
    private Integer dbMmrChangeAs;

    public String getRankedWinLostStatus() {
        return rankedWinLostStatus;
    }

    public String getRankedWinLost() {
        return rankedWinLost;
    }

    public Date getRankedDataTime() {
        return rankedDataTime;
    }

    public String getNext() {
        return next;
    }

    public int getDbTotalCasualWins() {
        return dbTotalCasualWins;
    }

    public int getDbTotalCasualLosses() {
        return dbTotalCasualLosses;
    }

    public int getDbTotalCasualKills() {
        return dbTotalCasualKills;
    }

    public int getDbTotalCasualDeaths() {
        return dbTotalCasualDeaths;
    }

    public int getDbTotalRankedWins() {
        return dbTotalRankedWins;
    }

    public int getDbTotalRankedLosses() {
        return dbTotalRankedLosses;
    }

    public int getDbTotalRankedKills() {
        return dbTotalRankedKills;
    }

    public int getDbTotalRankedDeaths() {
        return dbTotalRankedDeaths;
    }

    public int getDbTotalHs() {
        return dbTotalHs;
    }

    public Integer getDbCurrentMmrNa() {
        return dbCurrentMmrNa;
    }

    public Integer getDbCurrentMmrEu() {
        return dbCurrentMmrEu;
    }

    public Integer getDbCurrentMmrAs() {
        return dbCurrentMmrAs;
    }

    public Integer getDbMmrChangeNa() {
        return dbMmrChangeNa;
    }

    public Integer getDbMmrChangeEu() {
        return dbMmrChangeEu;
    }

    public Integer getDbMmrChangeAs() {
        return dbMmrChangeAs;
    }
}
