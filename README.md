# R6Tab-Java-API-Client
## Description
This java client provides the basic communication between a java program and the [R6Tab-API](https://github.com/Tabwire/R6Tab-API).

It uses [GSON](https://github.com/google/gson) as JSON converter and [OkHttp](https://square.github.io/okhttp/) as Web-Client.

## Developed on GitLab
**This project is developed and hosted on [GitLab](https://gitlab.com/siege-insights/r6tab-java-api-client) and just mirrored to [GitHub](https://github.com/RAYs3T/r6tab-java-api-client).**

**If you would like to raise an issue or contribute to this project feel free to do so on GitLab instead of GitHub. Thanks!**

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
If you use maven, simply add this code to your dependencies section of your project:
```xml
<dependencies>
...
    <!-- R6Tab API Wrapper https://gitlab.com/siege-insights/r6tab-java-api-client -->
    <dependency>
        <groupId>com.gitlab.siege-insights</groupId>
        <artifactId>r6tab-java-api-client</artifactId>
        <version>x.x</version><!-- Replace with current version number! -->
    </dependency>
</dependencies>
```

### Using the jar archive directly (not recommended)
You can go to the [central repo](https://search.maven.org/artifact/com.gitlab.siege-insights/r6tab-java-api-client/) and download the jar file directly from there.

This is not recommended, because of many reason, here a few of them:
1. You will have missing dependencies and have to provide them manually.
2. Updates are harder, not just writing down another version in the build.gradle or pom.xml
3. If we decide to change dependencies, manual work is required again.


### Code example

#### Basic usage
Create a new R6TabApi instance:

```java
class TestApplication {
    
    private void test() {
        R6TabApi api = new R6TabApiImpl();
        
        // Get player using the UUID
        Player player = api.getPlayerByUUID(UUID.fromString("21e4e8e4-b70a-4f8a-be4d-d0db7c8c9076"));
        
        // Prints the player name
        System.out.println(player.getName());
    }    
}

```

####
Upload an image to the R6Tab API and extract the players UUIDs from it: (beta)
```java
class TestApplication {
    
    private void test() {
        R6TabApi api = new R6TabApiImpl();
        
        // Extract UUIDs from a scoreboard screenshot
        List<UUID> ids = api.getUserUUIDFromScreenshot(imageFile);
        
        // Prints the player UUIDs
        for (UUID id : ids) {
            System.out.println(id);
        }
    }    
}

```


Have a look at the `R6TabApi` class for all the API functions

## TODO
This project is still work-in-progress


* [x] Implement other endpoints
  * [x] search
  * [x] leaderboards
* [x] Add support for image-download
