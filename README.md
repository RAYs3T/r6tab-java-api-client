# R6Tab-Java-API-Client
## Description
This java client provides the basic communication between a java program and the [R6Tab-API](https://github.com/Tabwire/R6Tab-API).

It uses [GSON](https://github.com/google/gson) as JSON converter and [OkHttp](https://square.github.io/okhttp/) as Web-Client.

## Usage
### Using a build-tool
#### Gradle
If you decide to use gradle, you just have to add the followering to your `dependencies` section inside of the `build.gradle` file.

```gradle
dependencies {
    // ...
    compile 'com.gitlab.siege-insights:r6tab-java-api-client'
}
```

It is recommended to use a fixed version. 
Have a look at the maven-central badge on the project dashboard.
The newest version is always displayed there.


#### Maven
TODO

### Using the jar archive directly (not recommended)
TODO

### Code example


Create a new R6TabApi instance:

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

Have a look at the `R6TabApi` class for all the API functions

### Examples
For some examples, have a look at [the examples dir](https://gitlab.com/siege-insights/r6tab-java-api-client/tree/master/src/test/java/com/gitlab/siegeinsights/r6tab/api/examples) in the test directory.

## TODO
This project is still work-in-progress


* [ ] Implement other endpoints
  * [x] search
  * [ ] leaderboards
* [ ] Add support for image-download