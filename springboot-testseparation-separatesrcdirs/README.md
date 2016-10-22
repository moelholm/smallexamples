# Spring Boot + Gradle : Different source directories based test separation

This example illustrates a simple approach to partition your tests into _unit tests_ and _integration tests_.

This approach:
- expects unit tests to be placed in the source directory `src/test/java`
- expects integration tests to be placed in the source directory `src/integrationTest/java`
- uses Gradle to honor the test directory separation

See `src/test/java`, `src/integrationTest/java` and `build.gradle`


### Running unit tests

    ./gradlew test
    
### Running integration tests

    ./gradlew integrationTest