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

    public void testSortRegion(){
        String[] values = {"p_currentmmr", "p_NA_currentmmr", "p_EU_currentmmr",
        "p_AS_currentmmr", "p_skillrating", "p_headshotacc"};
        for (Constants.SortRegion p : Constants.SortRegion.values()) {
            boolean knownValue = false;
            Assert.assertNotNull(p.toString());
            for (String v : values) {
                if (v.equals(p.getSortBy())) {
                    knownValue = true;
                }
            }
            Assert.assertTrue(knownValue);
        }
    }
}
