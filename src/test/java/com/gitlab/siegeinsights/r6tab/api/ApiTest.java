package com.gitlab.siegeinsights.r6tab.api;

import com.gitlab.siegeinsights.r6tab.api.entity.Player;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiService;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

@Test
public class ApiTest {
    public void playerApiTest() throws R6TabApiException, R6TabPlayerNotFoundException {
        R6TabApiService service = Mockito.mock(R6TabApiService.class);
        Mockito.when(service.getPlayerByUuid(Mockito.any())).thenReturn(new Player());

        R6TabApi api = new R6TabApiImpl(service);
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

        R6TabApi api = new R6TabApiImpl(service);
        api.getPlayerByUUID("test");
    }

}
