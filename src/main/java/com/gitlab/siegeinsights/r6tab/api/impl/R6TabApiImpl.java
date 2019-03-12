package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.Constants;
import com.gitlab.siegeinsights.r6tab.api.R6TabApi;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.R6TabPlayerNotFoundException;
import com.gitlab.siegeinsights.r6tab.api.entity.leaderboard.LeaderBoardEntry;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResult;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class R6TabApiImpl implements R6TabApi {

    private R6TabApiService service;

    public R6TabApiImpl() {
        service = new R6TabApiService(Constants.API_URL_BASE);
    }

    @Override
    public Player getPlayerByUUID(UUID uuid) throws R6TabApiException, R6TabPlayerNotFoundException {
        Player p = service.getPlayerByUuid(uuid);
        if (!p.isPlayerFound()) {
            throw new R6TabPlayerNotFoundException("Player with uuid: " + uuid + " was not found");
        }
        return p;
    }

    @Override
    public SearchResultWrapper searchPlayer(String playerName, Platform platform) throws R6TabApiException {
        return service.searchPlayer(playerName, platform);
    }

    @Override
    public List<LeaderBoardEntry> getLeaderBoard(Platform platform, Constants.SortRegion sortRegion) throws R6TabApiException {
        return service.getLeaderBoard(platform, sortRegion);
    }

    @Override
    public BufferedImage getUserAvatar(UUID userUuid) throws R6TabApiException {
        return service.getAvatar(userUuid);
    }

    @Override
    public File getUserAvatarFile(UUID userUuid) throws R6TabApiException {
        return service.getAvatarFile(userUuid);
    }

    @Override
    public BufferedImage getUserAvatar(Player player) throws R6TabApiException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        return service.getAvatar(player.getUser());
    }

    @Override
    public BufferedImage getUserAvatar(SearchResult searchResult) throws R6TabApiException {
        if (searchResult == null) {
            throw new IllegalArgumentException("searchResult cannot be null");
        }
        return service.getAvatar(searchResult.getUserUuid());
    }


}
