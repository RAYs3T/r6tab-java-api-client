package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.Player;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import org.testng.annotations.Test;

@Test(enabled = false)
public class ManualAPITest {

    public void test() throws R6TabApiException, R6TabPlayerNotFoundException {
        R6TabApi api = new R6TabApiImpl();
        Player p = api.getPlayerByUUID("21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076");
        System.out.println(p.toString());
    }
}
