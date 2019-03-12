package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.leaderboard.LeaderBoardEntry;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.impl.Mapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Test
public class LeaderBoardMappingTest {
    public void testLeaderBoardMapping() throws IOException, ParseException {
        URL url = Resources.getResource("leaderboard.json");
        String jsonResponse = Resources.toString(url, Charsets.UTF_8);
        Mapper m = new Mapper();
        List<LeaderBoardEntry> leaderboard = m.getLeaderBoardResultFromJson(jsonResponse);
        Assert.assertTrue(leaderboard.size() > 0);
        testFirstResult(leaderboard.get(0));
        int pos = 1;
        for (LeaderBoardEntry entry : leaderboard) {
            testCorrectPosition(entry, pos++);
        }
    }

    private void testFirstResult(LeaderBoardEntry first) {
        Assert.assertEquals(first.getPlayerUuid(), UUID.fromString("8fe620d5-b786-4b68-b43a-d4d460e8345a"));
        Assert.assertEquals(first.getPosition(), 1);
        Assert.assertEquals(first.getName(), "jpmk_");
        Assert.assertEquals(first.getLevel(), Integer.valueOf(169));
        Assert.assertEquals(first.getPlatformRaw(), "psn");
        Assert.assertEquals(first.getPlatform(), Platform.PLAYSTATION_NETWORK);
        Assert.assertEquals(first.getUserUuid(), UUID.fromString("a8813591-c077-428b-a52b-0435e6934a17"));
        Assert.assertEquals(first.getCurrentMmr(), Integer.valueOf(8591));
        Assert.assertEquals(first.getCurrentRank(), Integer.valueOf(20));
        Assert.assertEquals(first.getSkillRating(), Integer.valueOf(10092));
        Assert.assertEquals(first.getNaRank(), Integer.valueOf(19));
        Assert.assertEquals(first.getAsRank(), Integer.valueOf(20));
        Assert.assertEquals(first.getEuRank(), Integer.valueOf(0));
        Assert.assertEquals(first.getKd(), (float) 1.18);
        Assert.assertEquals(first.getHeadshotAccuracy(), Integer.valueOf(35420000));
    }

    private void testCorrectPosition(LeaderBoardEntry entry, int position) {
        Assert.assertEquals(entry.getPosition(), position);
    }
}
