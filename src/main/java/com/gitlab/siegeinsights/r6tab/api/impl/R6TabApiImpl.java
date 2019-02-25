package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.Constants;
import com.gitlab.siegeinsights.r6tab.api.R6TabApi;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.entity.Player;

public class R6TabApiImpl implements R6TabApi {

    private R6TabApiService service = new R6TabApiService(Constants.API_URL_BASE);

    public static final R6TabApi create() {
        return new R6TabApiImpl();
    }

    @Override
    public Player getPlayerByUUID(String uuid) throws R6TabApiException {
        return service.getPlayerByUuid(uuid);
    }
}
