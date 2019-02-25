# R6Tab-Java-API-Client
## Description
This java client provides the basic communication between a java program and the [R6Tab-API](https://github.com/Tabwire/R6Tab-API).

It uses [GSON](https://github.com/google/gson) as JSON converter and [OkHttp](https://square.github.io/okhttp/) as Web-Client.

## Usage
Just create a new instance: 

```java
class TestApplication {
    
    private void test() {
        R6TabApi api = new R6TabApiImpl();
        
        // Get player using the UUID
        Player player = api.getPlayerByUUID("21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076");
        
        // Prints the player name
        System.out.println(player.getName());
    }    
}

```