package com.gitlab.siegeinsights.r6tab.api.impl;

import com.gitlab.siegeinsights.r6tab.api.Constants;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.R6TabRequestTimeoutException;
import com.gitlab.siegeinsights.r6tab.api.entity.leaderboard.LeaderBoardEntry;
import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
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
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
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

    public Player getPlayerByUuid(UUID playerUuid) throws R6TabApiException {
        if (playerUuid == null) {
            throw new R6TabApiException("UUID cannot be null or empty");
        }

        String result = get(baseUrl + Constants.API_URL_PLAYER + "?p_id=" + playerUuid);
        return mapper.getPlayerFromJson(result);
    }


    /**
     * This is a work-a-round to force an update of the player data.
     * This is limited by time tho.
     *
     * @param playerUuid UUID of the player to update.
     * @throws R6TabApiException
     */
    public void pushPlayerUpdate(UUID playerUuid) throws R6TabApiException {
        if (playerUuid == null) {
            throw new R6TabApiException("UUID cannot be null or empty");
        }
        log.trace("Pushing player update for UUID: " + playerUuid + " ...");
        get(Constants.API_REFRESH_PLAYER_BASE + playerUuid + Constants.API_REFRESH_PLAYER_SUFFIX);
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
                    .append(URLEncoder.encode(playerName, "UTF-8"))
                    .append("&platform=")
                    .append(platform.getName());
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

    public List<LeaderBoardEntry> getLeaderBoard(Platform platform, Constants.SortRegion sort) throws R6TabApiException {
        if (platform == null) {
            throw new R6TabApiException("Platform cannot be null or empty");
        }

        if (sort == null) {
            throw new R6TabApiException("SortRegion cannot be null or empty");
        }

        String result = get(baseUrl + Constants.API_URL_LEADERBOARDS + "?sortplatform="
                + platform.getName() + "&sortregion=" + sort.getSortBy());
        return mapper.getLeaderBoardResultFromJson(result);
    }

    public File getAvatarFile(UUID userUuid) throws R6TabApiException {
        String requestUrl = Constants.UPLAY_AVATAR_PREFIX + userUuid.toString()
                + "/default_146_146.png";
        Request imageDownload = new Request.Builder().url(requestUrl).get().build();

        Response response;
        try {
            response = httpClient.newCall(imageDownload).execute();
        } catch (IOException e) {
            log.error("Unable to download avatar: " + e.getMessage(), e);
            throw new R6TabApiException("Avatar download failed: " + e.getMessage());
        }

        if (!response.isSuccessful()) {
            throw new R6TabApiException("Failed to download file: " + response);
        }
        try {
            File tmpFile = File.createTempFile("r6tab_avatar_", ".png");
            try (FileOutputStream fos = new FileOutputStream(tmpFile)) {
                if (response.body() == null) {
                    throw new R6TabApiException("Response is null");
                }
                fos.write(response.body().bytes());
                log.debug("Downloaded avatar from " + requestUrl + " and saved as: "
                        + tmpFile.getAbsolutePath());
                return tmpFile;
            } catch (FileNotFoundException e) {
                log.error("Unable to create temp file: " + e.getMessage(), e);
                throw new R6TabApiException("Unable to create temp file: " + e.getMessage());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new R6TabApiException(e.getMessage());
        }
    }

    public BufferedImage getAvatar(UUID userUuid) throws R6TabApiException {
        File tempFile = getAvatarFile(userUuid);
        try {
            return ImageIO.read(tempFile);
        } catch (IOException e) {
            log.error("Unable to download avatar: " + e.getMessage(), e);
            throw new R6TabApiException("Unable to download avatar: " + e.getMessage());
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

    // TODO Replace this with the real image type
    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpeg");

    /**
     * Uploads an image to the score2ranks interface and extracts the result URL from it
     *
     * @param url        Upload URL
     * @param screenshot the image
     * @return URL with the score2ranks id
     * @throws R6TabApiException
     */
    String postUploadImage(String url, File screenshot) throws R6TabApiException {
        log.debug("Executing web request with image upload: " + url);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fileToUpload", "score.jpg", RequestBody.create(MEDIA_TYPE_JPG, screenshot))
                .build();
        Request request = new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            if (response.code() != 200) {
                log.error("Request returned with status code " + response.code());
                if (response.body() != null && response.body().string().contains("cloudflare")) {
                    log.error("Request was properly blocked by cloudflare. Did you spam / abuse the API?");
                    System.err.println(response.body().toString());
                } else {
                    log.error("Return code does not match 200. Received: " + response.code());
                    throw new R6TabApiException("Request did not return with code 200 or body is empty. " +
                            "Received status code: " + response.code());
                }
            }

            // Extract new URL
            HttpUrl responseUrl = response.request().url();

            // Make sure the response includes the correct url
            if (!responseUrl.toString().contains("score2ranks")) {
                // Seems like the response is not correct. We expected something like score2ranks in the url.
                throw new R6TabApiException("No 'score2ranks' keyword found in received URL");
            }
            log.trace("Response from API is : " + responseUrl.toString().length() + " bytes long");
            return responseUrl.toString();
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
