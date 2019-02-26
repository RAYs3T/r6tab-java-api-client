package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.Constants;
import com.gitlab.siegeinsights.r6tab.api.R6TabApi;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.R6TabPlayerNotFoundException;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;

public class R6TabApiImpl implements R6TabApi {

    private R6TabApiService service;

    public R6TabApiImpl() {
        service = new R6TabApiService(Constants.API_URL_BASE);
    }

    @Override
    public Player getPlayerByUUID(String uuid) throws R6TabApiException, R6TabPlayerNotFoundException {
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
}
