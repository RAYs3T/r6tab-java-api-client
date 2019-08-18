package com.gitlab.siegeinsights.r6tab.api;

import java.util.regex.Pattern;

public final class Constants {
    public static final String API_URL_BASE = "https://r6tab.com/api/";

    public static final String API_URL_SEARCH = "search.php";

    public static final String API_URL_PLAYER = "player.php";

    // URL used to refresh the player data
    // Example: https://r6tab.com/21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076&updatenow=true
    public static final String API_REFRESH_PLAYER_BASE = "https://r6tab.com/mainpage.php?page=";
    public static final String API_REFRESH_PLAYER_SUFFIX = "&updatenow=true";

    public static final String API_URL_LEADERBOARDS = "leaderboards.php";

    public static final String UPLAY_AVATAR_PREFIX = "https://ubisoft-avatars.akamaized.net/";

    public static final String OCR_URL_UPLOAD = "https://r6tab.com/upload.php";


    public static final Pattern SCORE_2_RANK_REGEX = Pattern.compile(".*score2ranks\\-([a-z0-9]+)$");
    public static final Pattern SCORE_2_RANK_EXTRACT_REGEX =
            Pattern.compile("\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}");

    // URL for the score-2-ranks results
    public static final String SCORE_2_RANK_RESULT_URL_PREFIX = "https://r6tab.com/mainpage.php?page=/score2ranks-";

    public enum SortRegion {
        CURRENT_MMR("p_currentmmr"), // Sort players by current MMR
        CURRENT_MMR_NA("p_NA_currentmmr"), // Sort players by current MMR in American
        CURRENT_MMR_EU("p_EU_currentmmr"), // Sort players by current MMR in Europe
        CURRENT_MMR_AS("p_AS_currentmmr"), // Sort players by current MMR in Asia
        SKIL_RATING("p_skillrating"), // Sort players by Tabrank ELO
        HEADSHOT_ACCURACY("p_headshotacc"); // Sort players by headshot accuracy in precentage

        SortRegion(String sortBy) {
            this.sortBy = sortBy;
        }

        private String sortBy;

        public String getSortBy() {
            return sortBy;
        }

        @Override
        public String toString() {
            return getSortBy();
        }
    }
}
