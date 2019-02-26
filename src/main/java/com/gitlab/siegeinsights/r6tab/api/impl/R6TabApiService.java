package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.Constants;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.entity.Player;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class R6TabApiService {
    private Logger log = LoggerFactory.getLogger(R6TabApiService.class);

    private OkHttpClient httpClient = new OkHttpClient.Builder().build();

    private String baseUrl;

    private Mapper mapper = new Mapper();

    public R6TabApiService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Player getPlayerByUuid(String uuid) throws R6TabApiException {
        if (uuid == null || uuid.isEmpty()) {
            throw new R6TabApiException("UUID cannot be null or empty");
        }
        String result = get(baseUrl + Constants.API_URL_PLAYER + "?p_id=" + uuid);

        return mapper.getPlayerFromJson(result);
    }

    public String get(String url) throws R6TabApiException {
        log.debug("Executing web request: " + url);
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.code() != 200) { // We expect the request to be answered with 200 (OK)
                log.error("Request returned with status code " + response.code());
                if (response.body() != null && response.body().string().contains("cloudflare")) {
                    log.error("Request was properly blocked by cloudflare. Did you spam / abuse the API?");
                    throw new R6TabApiException("Cloudflare CDN seems to block our requests. Did you spam / abuse the API?");
                } else {
                    log.error("Return code does not match 200. Received: " + response.code());
                    throw new R6TabApiException("Request did not return with code 200 or body is empty. " +
                            "Received status code: " + response.code());
                }
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            log.error("API call failed with an IOException: " + e.getMessage(), e);
            throw new R6TabApiException("Call failed with an IOException");
        }
    }
}
