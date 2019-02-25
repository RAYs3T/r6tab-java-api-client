package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.R6TabRank;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class R6TabRankTest {

    private static Map<Integer, String> ranks = new HashMap<>();

    public R6TabRankTest() {
        ranks.put(1, "copper 4");
        ranks.put(2, "copper 3");
        ranks.put(3, "copper 2");
        ranks.put(4, "copper 1");

        ranks.put(5, "bronze 4");
        ranks.put(6, "bronze 3");
        ranks.put(7, "bronze 2");
        ranks.put(8, "bronze 1");

        ranks.put(9, "silver 4");
        ranks.put(10, "silver 3");
        ranks.put(11, "silver 2");
        ranks.put(12, "silver 1");

        ranks.put(13, "gold 4");
        ranks.put(14, "gold 3");
        ranks.put(15, "gold 2");
        ranks.put(16, "gold 1");

        ranks.put(17, "platinum 3");
        ranks.put(18, "platinum 2");
        ranks.put(19, "platinum 1");

        ranks.put(20, "diamond 1");
    }

    @Test
    public void rankMapperGetByNameTest() {
        Assert.assertTrue(ranks.size() > 0);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(1)), R6TabRank.COPPER_4);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(2)), R6TabRank.COPPER_3);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(3)), R6TabRank.COPPER_2);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(4)), R6TabRank.COPPER_1);

        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(5)), R6TabRank.BRONZE_4);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(6)), R6TabRank.BRONZE_3);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(7)), R6TabRank.BRONZE_2);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(8)), R6TabRank.BRONZE_1);


        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(9)), R6TabRank.SILVER_4);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(10)), R6TabRank.SILVER_3);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(11)), R6TabRank.SILVER_2);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(12)), R6TabRank.SILVER_1);


        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(13)), R6TabRank.GOLD_4);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(14)), R6TabRank.GOLD_3);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(15)), R6TabRank.GOLD_2);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(16)), R6TabRank.GOLD_1);

        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(17)), R6TabRank.PLATINUM_3);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(18)), R6TabRank.PLATINUM_2);
        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(19)), R6TabRank.PLATINUM_1);

        Assert.assertEquals(R6TabRank.getRankByName(ranks.get(20)), R6TabRank.DIAMOND);
    }

    @Test
    public void rankMapperByIdTest() {
        Assert.assertEquals(R6TabRank.getRankById(1).getName(), ranks.get(1));
        Assert.assertEquals(R6TabRank.getRankById(2).getName(), ranks.get(2));
        Assert.assertEquals(R6TabRank.getRankById(3).getName(), ranks.get(3));
        Assert.assertEquals(R6TabRank.getRankById(4).getName(), ranks.get(4));

        Assert.assertEquals(R6TabRank.getRankById(5).getName(), ranks.get(5));
        Assert.assertEquals(R6TabRank.getRankById(6).getName(), ranks.get(6));
        Assert.assertEquals(R6TabRank.getRankById(7).getName(), ranks.get(7));
        Assert.assertEquals(R6TabRank.getRankById(8).getName(), ranks.get(8));

        Assert.assertEquals(R6TabRank.getRankById(9).getName(), ranks.get(9));
        Assert.assertEquals(R6TabRank.getRankById(10).getName(), ranks.get(10));
        Assert.assertEquals(R6TabRank.getRankById(11).getName(), ranks.get(11));
        Assert.assertEquals(R6TabRank.getRankById(12).getName(), ranks.get(12));

        Assert.assertEquals(R6TabRank.getRankById(13).getName(), ranks.get(13));
        Assert.assertEquals(R6TabRank.getRankById(14).getName(), ranks.get(14));
        Assert.assertEquals(R6TabRank.getRankById(15).getName(), ranks.get(15));
        Assert.assertEquals(R6TabRank.getRankById(16).getName(), ranks.get(16));

        Assert.assertEquals(R6TabRank.getRankById(17).getName(), ranks.get(17));
        Assert.assertEquals(R6TabRank.getRankById(18).getName(), ranks.get(18));
        Assert.assertEquals(R6TabRank.getRankById(19).getName(), ranks.get(19));

        Assert.assertEquals(R6TabRank.getRankById(20).getName(), ranks.get(20));
    }
}
