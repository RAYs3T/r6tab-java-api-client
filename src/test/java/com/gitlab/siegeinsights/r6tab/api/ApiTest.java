package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.player.Player;
import com.gitlab.siegeinsights.r6tab.api.entity.search.Platform;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResult;
import com.gitlab.siegeinsights.r6tab.api.entity.search.SearchResultWrapper;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiService;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

@Test
public class ApiTest {
    public void playerApiTest() throws R6TabApiException, R6TabPlayerNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchFieldException {
        // Modify private field
        Class<?> clazz = Player.class;
        Object modifiedPlayer = clazz.newInstance();

        Field foundField = modifiedPlayer.getClass().getDeclaredField("playerFound");
        foundField.setAccessible(true);
        foundField.set(modifiedPlayer, true);

        Player dummy = (Player) modifiedPlayer;

        R6TabApiService service = Mockito.mock(R6TabApiService.class);
        Mockito.when(service.getPlayerByUuid(Mockito.any())).thenReturn(dummy);

        R6TabApi api = getApiMock(service);
        api.getPlayerByUUID(UUID.fromString("2353cb84-f1b8-4514-9368-28c5cbe9e708"));
    }

    @Test(expectedExceptions = R6TabPlayerNotFoundException.class)
    public void playerApiNotFoundTest() throws R6TabApiException,
            R6TabPlayerNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        // Modify private field
        Class<?> clazz = Player.class;
        Object modifiedPlayer = clazz.newInstance();

        Field foundField = modifiedPlayer.getClass().getDeclaredField("playerFound");
        foundField.setAccessible(true);
        foundField.set(modifiedPlayer, false);

        Player dummy = (Player) modifiedPlayer;

        R6TabApiService service = Mockito.mock(R6TabApiService.class);
        Mockito.when(service.getPlayerByUuid((Mockito.any()))).thenReturn(dummy);

        R6TabApi api = getApiMock(service);
        api.getPlayerByUUID(UUID.fromString("2353cb84-f1b8-4514-9368-28c5cbe9e708"));
    }

    public void searchApiTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException, R6TabApiException {


        // Modify private field
        Class<?> clazz = SearchResultWrapper.class;
        Object m = clazz.newInstance();

        Field foundField = m.getClass().getDeclaredField("results");
        foundField.setAccessible(true);
        foundField.set(m, new ArrayList<SearchResult>());

        SearchResultWrapper dummyResult = (SearchResultWrapper) m;

        R6TabApiService service = Mockito.mock(R6TabApiService.class);
        Mockito.when(service.searchPlayer(Mockito.eq("CrazyTestDude"), Mockito.any())).thenReturn(dummyResult);
        R6TabApi api = getApiMock(service);
        api.searchPlayer("CrazyTestDude", Platform.UPLAY);
    }

    private R6TabApi getApiMock(R6TabApiService mockService) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        // Modify private field
        Class<?> clazz = R6TabApiImpl.class;
        Object modifiedApi = clazz.newInstance();

        Field foundField = modifiedApi.getClass().getDeclaredField("service");
        foundField.setAccessible(true);
        foundField.set(modifiedApi, mockService);

        return (R6TabApiImpl) modifiedApi;
    }

}
