package com.gitlab.siegeinsights.r6tab.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Test
public class ConstantsTest {

    public void testBaseUrl() {
        validateUrl(Constants.API_URL_BASE);
    }

    public void testPlayerUrl() {
        validateUrl(Constants.API_URL_BASE + Constants.API_URL_PLAYER);
    }

    public void testLeaderboardUrl() {
        validateUrl(Constants.API_URL_BASE + Constants.API_URL_LEADERBOARDS);
    }

    public void testSearchUrl() {
        validateUrl(Constants.API_URL_BASE + Constants.API_URL_SEARCH);
    }

    private void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            Assert.fail("Error creating URL from: " + url);
        }
    }

    public void testSortPlatforms() {
        String[] values = {"uplay", "psn", "xbl"};
        for (Constants.SortPlatform p : Constants.SortPlatform.values()) {
            boolean knownValue = false;
            for (String v : values) {
                if (v.equals(p.getName())) {
                    knownValue = true;
                }
            }
            Assert.assertTrue(knownValue);
        }

        for (String v : values) {
            Assert.assertNotNull(Constants.SortPlatform.getByName(v));
        }
    }
}
