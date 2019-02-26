package com.gitlab.siegeinsights.r6tab.api.examples;

import com.gitlab.siegeinsights.r6tab.api.R6TabApi;
import com.gitlab.siegeinsights.r6tab.api.R6TabApiException;
import com.gitlab.siegeinsights.r6tab.api.R6TabPlayerNotFoundException;
import com.gitlab.siegeinsights.r6tab.api.entity.Player;
import com.gitlab.siegeinsights.r6tab.api.impl.R6TabApiImpl;

public class ApiUsageExample {

    public static void main(String[] args) {
        Player p = getPlayer();
        if (p != null) {
            System.out.println("Player name: " + p.getName());
        }
    }

    private static Player getPlayer() {
        R6TabApi api = new R6TabApiImpl();
        try {
            return api.getPlayerByUUID("21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076");
        } catch (R6TabApiException e) {
            // An unknown exception occurred (server error, spam problem?)
            e.printStackTrace();
            return null;
        } catch (R6TabPlayerNotFoundException e) {
            // The player was not found
            // Handle this in your program!
            System.err.println("Player not found");
            return null;
        }
    }
}
