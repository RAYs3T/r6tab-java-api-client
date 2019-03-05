package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;

import java.util.UUID;

public interface R6TabApi {

    /**
     * Retrieves a player by a specific UUID.
     *
     * @param uuid UPlay UUID of the player.
     * @return A <code>Player</code> object with all the subtree.
     * @throws R6TabApiException            When the API call didn't succeed.
     * @throws R6TabPlayerNotFoundException When the player was not found
     */
    Player getPlayerByUUID(UUID uuid) throws R6TabApiException, R6TabPlayerNotFoundException;


    /**
     * Finds the player by name and platform.
     *
     * @param playerName Name of the searched player
     * @param platform   <code>Platform</code> or null for all platforms
     * @throws R6TabApiException When the API call didn't succeed.
     * @return All results
     */
    SearchResultWrapper searchPlayer(String playerName, Platform platform) throws R6TabApiException;
}
