package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.Constants;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.R6TabRequestTimeoutException;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;


public class R6TabApiService {
    private Logger log = LoggerFactory.getLogger(R6TabApiService.class);

    private OkHttpClient httpClient;

    private String baseUrl;

    private Mapper mapper = new Mapper();

    /**
     * Default constructor, recommended for normal API use.
     */
    public R6TabApiService() {
        this(30 * 1000); // Default request timeout 30 seconds
    }

    /**
     * Sets a custom API url and timeout (used for testing)
     *
     * @param baseUrl API url
     * @param timeout timeout in milliseconds
     */
    public R6TabApiService(String baseUrl, long timeout) {
        this(timeout);
        this.baseUrl = baseUrl;
    }

    /**
     * Sets a custom timeout for the API requests
     *
     * @param timeout amount of milliseconds to wait for a web-request to return, before failing
     */
    public R6TabApiService(long timeout) {
        httpClient = new OkHttpClient.
                Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .callTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * Uses a different API base url. This is only used for testing against a local webserver.
     *
     * @param baseUrl API base url
     */
    public R6TabApiService(String baseUrl) {
        this();
        this.baseUrl = baseUrl;
    }


    public Player getPlayerByUuid(String uuid) throws R6TabApiException {
        if (uuid == null || uuid.isEmpty()) {
            throw new R6TabApiException("UUID cannot be null or empty");
        }
        String result = get(baseUrl + Constants.API_URL_PLAYER + "?p_id=" + uuid);

        return mapper.getPlayerFromJson(result);
    }

    public SearchResultWrapper searchPlayer(String playerName, Platform platform) throws R6TabApiException {
        if (playerName == null) {
            throw new R6TabApiException("playerName cannot be empty");
        }
        if (platform == null) {
            throw new R6TabApiException("platform cannot be null");
        }

        try {
            StringBuilder requestUrlBuilder = new StringBuilder()
                    .append(baseUrl)
                    .append(Constants.API_URL_SEARCH)
                    .append("?search=")
                    .append(URLEncoder.encode(playerName, "UTF-8"));
            if (platform != null) {
                requestUrlBuilder.append("&platform=")
                        .append(platform.getName());
            }
            String response = get(requestUrlBuilder.toString());
            SearchResultWrapper resultWrapper = mapper.getSearchResultsFromJson(response);
            if (!resultWrapper.isValidResultsCount()) {
                throw new R6TabApiException(
                        "Result count does not match actual resultWrapper that has been returned");
            }
            return resultWrapper;
        } catch (UnsupportedEncodingException e) {
            throw new R6TabApiException("Encoding error: " + e.getMessage());
        }
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
            String responseString = response.body() != null ? response.body().string() : null;
            log.trace("Response from API is : " + responseString.length() + " bytes long");
            return responseString;
        } catch (SocketTimeoutException e) {
            log.debug("API call timed out: " + e.getMessage(), e);
            throw new R6TabRequestTimeoutException("API request timed out");
        } catch (InterruptedIOException e) {
            log.debug("API call timed out (interrupted): " + e.getMessage(), e);
            throw new R6TabRequestTimeoutException("API request timed out (interrupted)");
        } catch (IOException e) {
            log.error("API call failed with an IOException: " + e.getMessage(), e);
            throw new R6TabApiException("Call failed with an IOException");
        }
    }
}
