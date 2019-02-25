package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import org.testng.annotations.Test;

@Test
public class ApiTest {
    public void playerApiTest() throws R6TabApiException {
        R6TabApi api = R6TabApiImpl.create();
        api.getPlayerByUUID("test");
    }

}
