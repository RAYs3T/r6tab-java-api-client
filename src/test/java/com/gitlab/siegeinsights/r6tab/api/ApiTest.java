package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.Player;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiService;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

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
        api.getPlayerByUUID("test");
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
        Mockito.when(service.getPlayerByUuid(Mockito.any())).thenReturn(dummy);

        R6TabApi api = getApiMock(service);
        api.getPlayerByUUID("test");
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
