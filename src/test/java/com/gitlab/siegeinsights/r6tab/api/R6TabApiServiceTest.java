package com.gitlab.siegeinsights.r6tab.api;

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

@Test
public class R6TabApiServiceTest {

    private static final String URL_LOCAL_SERVER = "http://localhost:8000";

    private R6TabApiService service;
    HttpServer httpServer;

    @BeforeClass
    public void setupTestServer() throws IOException {
        service = new R6TabApiService(URL_LOCAL_SERVER);

        httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/" + Constants.API_URL_PLAYER, new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {

                URL url = Resources.getResource("playerdatabyid.json");
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
        httpServer.start();
    }

    @Test
    public void apiTest() throws R6TabApiException {
        String result = service.get(URL_LOCAL_SERVER + "/" + Constants.API_URL_PLAYER);
        Assert.assertNotNull(result);
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void invalidApiTest() throws R6TabApiException {
        service.get(URL_LOCAL_SERVER + "/invalid_url");
    }

    @Test(expectedExceptions = R6TabApiException.class)
    public void serverErrorApiTest() throws R6TabApiException {
        service.get(URL_LOCAL_SERVER + "/500");
    }

    @AfterClass
    public void shutdownHttpServer() {
        httpServer.stop(0);
    }


}
