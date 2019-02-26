package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.player.R6TabRank;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResult;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import com.gitlab.siegeinsights.r6tab.api.impl.Mapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

@Test
public class SearchMappingTest {
    public void testSearchMapping() throws IOException, ParseException {
        URL url = Resources.getResource("search_result.json");
        String jsonResponse = Resources.toString(url, Charsets.UTF_8);
        Mapper m = new Mapper();
        SearchResultWrapper sr = m.getSearchResultsFromJson(jsonResponse);
        Assert.assertEquals(sr.getTotalResults(), Integer.valueOf(2));
        Assert.assertTrue(sr.isValidResultsCount());

        validateFirstResult(sr.getResults().get(0));
        validateSecondResult(sr.getResults().get(1));
    }

    private void validateFirstResult(SearchResult r1) {
        Assert.assertEquals(r1.getUuid(), "21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076");
        Assert.assertEquals(r1.getName(), "RAYs3T.pTL");
        Assert.assertEquals(r1.getLevel(), Integer.valueOf(1337));
        Assert.assertEquals(r1.getPlatform(), Platform.UPLAY);
        Assert.assertEquals(r1.getUserUuid(), "21e4e8e4-be4d-be4d-be4d-d0db7c8c9076");
        Assert.assertEquals(r1.getCurrentMmr(), Integer.valueOf(3979));
        Assert.assertEquals(r1.getCurrentRank(), R6TabRank.getRankById(18));
        Assert.assertEquals(r1.getKd(), 1.05f);
    }

    private void validateSecondResult(SearchResult r2) {
        Assert.assertEquals(r2.getUuid(), "83518584-ac1e-4a56-838c-be78f02a523b");
        Assert.assertEquals(r2.getName(), "BaIIer101");
        Assert.assertEquals(r2.getLevel(), Integer.valueOf(24));
        Assert.assertEquals(r2.getPlatform(), Platform.UPLAY);
        Assert.assertEquals(r2.getUserUuid(), "83518584-ac1e-4a56-838c-be78f02a523b");
        Assert.assertEquals(r2.getCurrentMmr(), Integer.valueOf(0));
        Assert.assertNull(r2.getCurrentRank());
        Assert.assertNull(r2.getKd());
    }
}
