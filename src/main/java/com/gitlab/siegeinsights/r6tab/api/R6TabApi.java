package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.Player;

public interface R6TabApi {

    /**
     * Retrieves a player by a specific UUID.
     *
     * @param uuid UPlay UUID of the player.
     * @return A <code>Player</code> object with all the subtree.
     * @throws R6TabApiException When the API call didn't succeed.
     * @throws R6TabPlayerNotFoundException When the player was not found
     */
    public Player getPlayerByUUID(String uuid) throws R6TabApiException, R6TabPlayerNotFoundException;
}
