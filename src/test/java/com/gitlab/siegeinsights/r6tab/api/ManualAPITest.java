package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.leaderboard.LeaderBoardEntry;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

/**
 * Just for manual testing
 */
@Test(enabled = false)
public class ManualAPITest {

    public void test() throws R6TabApiException, R6TabPlayerNotFoundException {
        R6TabApi api = new R6TabApiImpl();
        Player p = api.getPlayerByUUID(UUID.fromString("21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076"));
        System.out.println(p.toString());
    }

    public void testSearch() throws R6TabApiException {
        R6TabApi api = new R6TabApiImpl();
        SearchResultWrapper results = api.searchPlayer("RAYs3T.pTL", Platform.UPLAY);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.getResults().size() > 0);
        Assert.assertEquals(results.getResults().get(0).getPlatform(), Platform.UPLAY);
    }

    public void testLeaderboard() throws R6TabApiException {
        R6TabApi api = new R6TabApiImpl();
        List<LeaderBoardEntry> results = api.getLeaderBoard(Platform.UPLAY, Constants.SortRegion.CURRENT_MMR);
        Assert.assertNotNull(results);
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void testSearchWithoutPlatform() throws R6TabApiException {
        R6TabApi api = new R6TabApiImpl();
        api.searchPlayer("RAYs3T.pTL", null);
    }
}
