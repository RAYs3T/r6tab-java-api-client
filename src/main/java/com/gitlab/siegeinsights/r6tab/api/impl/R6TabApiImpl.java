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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

public class R6TabApiImpl implements R6TabApi {

    private Logger log = LoggerFactory.getLogger(R6TabApi.class);

    private R6TabApiService service;

    public R6TabApiImpl() {
        service = new R6TabApiService(Constants.API_URL_BASE);
    }

    @Override
    public Player getPlayerByUUID(UUID uuid) throws R6TabApiException, R6TabPlayerNotFoundException {
        return getPlayerByUUID(uuid, false);
    }

    @Override
    public Player getPlayerByUUID(UUID uuid, boolean pushUpdate) throws R6TabApiException, R6TabPlayerNotFoundException {
        if (pushUpdate) {
            // Push a player update
            // This may or may not succeed, depending on the time that has been past, after the last update.
            service.pushPlayerUpdate(uuid);
        }

        // Fetch the player information
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

    public void pushPlayerUpdate(UUID playerUuid) throws R6TabApiException {
        service.pushPlayerUpdate(playerUuid);
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

    @Override
    public List<UUID> getUserUUIDFromScreenshot(File image) throws R6TabApiException {
        log.debug("Uploading screenshot ...");
        String scoreResultUrl = service.postUploadImage(Constants.OCR_URL_UPLOAD, image);
        log.debug("Extracting result URL ...");
        Matcher matcher = Constants.SCORE_2_RANK_REGEX.matcher(scoreResultUrl);
        if (!matcher.matches()) {
            throw new R6TabApiException("Unable to retrieve correct URL for results");
        }

        // Extract the result ID from the URL
        String resultId = matcher.group(1);
        // Now that we have the resultId, we fetch the result page ...
        log.debug("Fetching result page ...");
        String resultPageHtml = service.get(Constants.SCORE_2_RANK_RESULT_URL_PREFIX + resultId);

        Matcher playerMatches = Constants.SCORE_2_RANK_EXTRACT_REGEX.matcher(resultPageHtml);
        List<UUID> foundPlayers = new ArrayList<>();
        while (playerMatches.find()) {
            UUID match = UUID.fromString(playerMatches.group(0));
            if (!foundPlayers.contains(match)) {
                foundPlayers.add(match);
            }
        }

        if (foundPlayers.size() == 0) {
            log.info("Unable to extract any players from screenshot. Result page did not contain any players");
            return null;

        }

        log.info("Found " + foundPlayers.size() + " players via uploaded screenshot");
        return foundPlayers;

    }

}
