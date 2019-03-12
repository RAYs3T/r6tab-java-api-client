package com.gitlab.siegeinsights.r6tab.api;

public final class Constants {
    public static final String API_URL_BASE = "https://r6tab.com/api/";

    public static final String API_URL_SEARCH = "search.php";

    public static final String API_URL_PLAYER = "player.php";

    public static final String API_URL_LEADERBOARDS = "leaderboards.php";

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
