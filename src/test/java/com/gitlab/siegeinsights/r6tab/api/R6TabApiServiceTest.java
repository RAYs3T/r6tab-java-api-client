package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiService;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.UUID;

@Test
public class R6TabApiServiceTest {

    private static final String URL_LOCAL_SERVER = "http://localhost:8000/";

    HttpServer httpServer;

    @BeforeClass
    public void setupTestServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/test", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {

                URL url = Resources.getResource("playerdatabyid.json");
                String jsonResponse = Resources.toString(url, Charsets.UTF_8);
                byte[] response = jsonResponse.getBytes();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            }
        });

        httpServer.createContext("/player.php", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {

                URL url = Resources.getResource("playerdatabyid.json");
                String jsonResponse = Resources.toString(url, Charsets.UTF_8);
                byte[] response = jsonResponse.getBytes();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            }
        });

        httpServer.createContext("/invalid_user/" + Constants.API_URL_PLAYER, new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {

                URL url = Resources.getResource("playerdatanotfound.json");
                String jsonResponse = Resources.toString(url, Charsets.UTF_8);
                byte[] response = jsonResponse.getBytes();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            }
        });

        httpServer.createContext("/search.php", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {

                URL url = Resources.getResource("search_result.json");
                String jsonResponse = Resources.toString(url, Charsets.UTF_8);
                byte[] response = jsonResponse.getBytes();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            }
        });

        httpServer.createContext("/500", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                exchange.close();
            }
        });

        httpServer.createContext("/delayed/player.php", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    Thread.sleep(500);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    exchange.close();
                } catch (InterruptedException e) {
                } finally {

                }
            }
        });
        httpServer.start();
    }

    @Test
    public void apiTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER);
        String result = service.get(URL_LOCAL_SERVER + "test");
        Assert.assertNotNull(result);
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void invalidApiTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER);
        service.get(URL_LOCAL_SERVER + "/invalid_test");
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void serverErrorApiTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER);
        service.get(URL_LOCAL_SERVER + "500");
    }

    @Test
    public void playerApiTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER + Constants.API_URL_PLAYER);
        Player p = service.getPlayerByUuid(UUID.fromString("2353cb84-f1b8-4514-9368-28c5cbe9e708"));
        Assert.assertNotNull(p);
        Assert.assertNotNull(p.getSocial());
        Assert.assertNotNull(p.getMatches());
        Assert.assertEquals(p.getMatches().size(), 2);
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void playerApiInvalidTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER + "invalid_user" + Constants.API_URL_PLAYER);
        service.getPlayerByUuid(UUID.fromString("7bb6c348-09d3-487c-91ed-312aa4bd8f2b"));
    }


    @Test
    public void searchPlayerTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER + Constants.API_URL_SEARCH);
        SearchResultWrapper sr = service.searchPlayer("test_name", Platform.UPLAY);
        Assert.assertTrue(sr.getResults().size() > 0);
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void searchPlayerInvalidTest() throws R6TabApiException {
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER + "invalid" + Constants.API_URL_SEARCH);
        service.searchPlayer("test_name", Platform.UPLAY);
    }


    @Test(expectedExceptions = R6TabRequestTimeoutException.class, timeOut = 2000)
    public void timeoutTest() throws R6TabApiException {
        // Test if the timeout is handled correct
        // Expected to fail after 100ms
        R6TabApiService service = new R6TabApiService(URL_LOCAL_SERVER + "delayed/", 100);
        service.getPlayerByUuid(UUID.fromString("2353cb84-f1b8-4514-9368-28c5cbe9e708"));
        Assert.fail("The call did return, but we expected it to time out...");
    }

    @AfterClass
    public void shutdownHttpServer() {
        httpServer.stop(0);
    }


}
