package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.entity.leaderboard.LeaderBoardEntry;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class Mapper {
    private Gson gson;

    public Mapper() {
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat("dd/MM/yy");
        gson = gb.create();
    }

    public Player getPlayerFromJson(String json) {
        Player p = gson.fromJson(json, Player.class);
        return p;
    }

    public SearchResultWrapper getSearchResultsFromJson(String json) {
        return gson.fromJson(json, SearchResultWrapper.class);
    }

    public List<LeaderBoardEntry> getLeaderBoardResultFromJson(String json) {
        Type listType = new TypeToken<List<LeaderBoardEntry>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
}
