package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Test
public class ScreenshotOCRUploadTest {

    public void testOCRUploadUUIDRetriever() throws R6TabApiException {
        R6TabApi api = new R6TabApiImpl();
        List<UUID> foundPlayers =
                api.getUserUUIDFromScreenshot(
                        new File(getClass().getClassLoader().getResource("scoreboard.jpg").getFile())
                );
        // Test if our test screenshot's user are found in the results ... (some of them)
        Assert.assertTrue(foundPlayers.contains(UUID.fromString("6ad9031b-2617-4a5d-8b0e-21d665835ad0")),
                "Unable to find expected user in results");
        Assert.assertTrue(foundPlayers.contains(UUID.fromString("e4fa2a48-4321-4930-99f0-dc1b66927610")),
                "Unable to find expected user in results");
        for (UUID id : foundPlayers) {
            System.out.println(id);
        }
    }
}
