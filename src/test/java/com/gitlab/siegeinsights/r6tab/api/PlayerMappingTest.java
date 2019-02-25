package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.Match;
import com.gitlab.siegeinsights.r6tab.api.entity.Player;
import com.gitlab.siegeinsights.r6tab.api.impl.Mapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Test
public class PlayerMappingTest {

    private SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy");

    public void testPlayerMapping() throws IOException, ParseException {
        URL url = Resources.getResource("playerdatabyid.json");
        String jsonResponse = Resources.toString(url, Charsets.UTF_8);
        Mapper m = new Mapper();
        Player p = m.getPlayerFromJson(jsonResponse);

        validateSocial(p);

        validateMatch1(p.getMatches().get(0));
        validateMatch2(p.getMatches().get(1));

        Assert.assertEquals(p.getPlayerId(), "9bd44bde-9c48-48ae-9c2b-4e11e4b16083");
        Assert.assertEquals(p.getUser(), "9c48-9c48-48ae-9c2b-4e11e4b16083");
    }


    private void validateSocial(Player p) {
        Assert.assertEquals(p.getSocial().getTwitter(), "tabwiretwitter");
        Assert.assertEquals(p.getSocial().getInstagram(), "tabwireinsta");
        Assert.assertEquals(p.getSocial().getTwitch(), "twitchacc");
        Assert.assertEquals(p.getSocial().getYoutube(), "https://www.youtube.com/testyt");
        Assert.assertEquals(p.getSocial().getBio(), "Tabwire Java API");
        Assert.assertEquals(p.getSocial().getEsl(), "https://play.eslgaming.com/player/13371337");
        Assert.assertEquals(p.getSocial().getDiscord(), "https://discord.gg/nonexistentdiscord");
        Assert.assertEquals(p.getSocial().getAliases(), 1);
        Assert.assertEquals(p.getSocial().getEmbed(), "Ia3Qw2GNmqU");
    }

    private void validateMatch1(Match m1) throws ParseException {
        Assert.assertEquals(m1.getRankedWinLostStatus(), "lost");
        Assert.assertEquals(m1.getRankedWinLost(), "4 Won, 4 Lost");
        Assert.assertEquals(m1.getRankedDataTime(), parser.parse("02/18/19"));
        Assert.assertEquals(m1.getNext(), "default");

        Assert.assertEquals(m1.getDbTotalCasualWins(), 10);
        Assert.assertEquals(m1.getDbTotalCasualLosses(), 20);
        Assert.assertEquals(m1.getDbTotalCasualKills(), 30);
        Assert.assertEquals(m1.getDbTotalCasualDeaths(), 40);
        Assert.assertEquals(m1.getDbTotalRankedWins(), 50);
        Assert.assertEquals(m1.getDbTotalRankedLosses(), 60);
        Assert.assertEquals(m1.getDbTotalRankedKills(), 70);
        Assert.assertEquals(m1.getDbTotalRankedDeaths(), 80);
        Assert.assertEquals(m1.getDbTotalHs(), 90);

        Assert.assertEquals(m1.getDbCurrentMmrNa(), Integer.valueOf(10123));
        Assert.assertEquals(m1.getDbCurrentMmrEu(), Integer.valueOf(20123));
        Assert.assertEquals(m1.getDbCurrentMmrAs(), Integer.valueOf(30123));

        Assert.assertEquals(m1.getDbMmrChangeNa(), Integer.valueOf(15));
        Assert.assertEquals(m1.getDbMmrChangeEu(), Integer.valueOf(16));
        Assert.assertEquals(m1.getDbMmrChangeAs(), Integer.valueOf(17));
    }

    private void validateMatch2(Match m2) throws ParseException {
        Assert.assertEquals(m2.getRankedWinLostStatus(), "won");
        Assert.assertEquals(m2.getRankedWinLost(), "2 Won");
        Assert.assertEquals(m2.getRankedDataTime(), parser.parse("02/17/19"));
        Assert.assertEquals(m2.getNext(), "next");

        Assert.assertEquals(m2.getDbTotalCasualWins(), 1);
        Assert.assertEquals(m2.getDbTotalCasualLosses(), 2);
        Assert.assertEquals(m2.getDbTotalCasualKills(), 3);
        Assert.assertEquals(m2.getDbTotalCasualDeaths(), 4);
        Assert.assertEquals(m2.getDbTotalRankedWins(), 5);
        Assert.assertEquals(m2.getDbTotalRankedLosses(), 6);
        Assert.assertEquals(m2.getDbTotalRankedKills(), 7);
        Assert.assertEquals(m2.getDbTotalRankedDeaths(), 8);
        Assert.assertEquals(m2.getDbTotalHs(), 9);

        Assert.assertEquals(m2.getDbCurrentMmrNa(), Integer.valueOf(3962));
        Assert.assertEquals(m2.getDbCurrentMmrEu(), Integer.valueOf(3183));
        Assert.assertEquals(m2.getDbCurrentMmrAs(), null);

        Assert.assertEquals(m2.getDbMmrChangeNa(), Integer.valueOf(33));
        Assert.assertEquals(m2.getDbMmrChangeEu(), null);
        Assert.assertEquals(m2.getDbMmrChangeAs(), null);
    }
}
