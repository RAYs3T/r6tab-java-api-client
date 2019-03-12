package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.leaderboard.LeaderBoardEntry;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResult;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
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
     * @return All results
     * @throws R6TabApiException When the API call didn't succeed.
     */
    SearchResultWrapper searchPlayer(String playerName, Platform platform) throws R6TabApiException;


    /**
     * Lists the current leaderboard filtered by the platform and sorted by the region
     *
     * @param platform   The platform to filter
     * @param sortRegion Sorting
     * @return
     */
    List<LeaderBoardEntry> getLeaderBoard(Platform platform, Constants.SortRegion sortRegion) throws R6TabApiException;


    /**
     * Downloads and returns the users avatar used in uplay by the userUuid
     *
     * @param userUuid The USERUuid, this is not the playerUuid!
     * @return The downloaded avatar image
     */
    BufferedImage getUserAvatar(UUID userUuid) throws R6TabApiException;

    /**
     * Downloads a users avatar and stores it into a temporary file.
     *
     * @param userUuid The users uuid not the player uuid.
     * @return The downloaded temporary file.
     */
    File getUserAvatarFile(UUID userUuid) throws R6TabApiException;

    /**
     * Downloads and returns the users avatar used in uplay by player.
     *
     * @param player The player object where to extract the user uuid from.
     * @return The downloaded avatar image
     */
    BufferedImage getUserAvatar(Player player) throws R6TabApiException;

    /**
     * Downloads a users avatar in uplay by using the search result.
     *
     * @param searchResult Result to extract the user uuid from.
     * @return
     */
    BufferedImage getUserAvatar(SearchResult searchResult) throws R6TabApiException;

}