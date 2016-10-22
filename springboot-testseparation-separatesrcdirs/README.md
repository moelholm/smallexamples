# Spring Boot + Gradle : Name based test separation

This example illustrates a simple approach to partition your tests into _unit tests_ and _integration tests_.

This approach:
- allows you to keep all tests in the same source directory `src/test/java`
- is name based: integration tests are suffixed with `IntegrationTests`
- uses Gradle to partition the tests using _include_/_exclude_ name filters

See `src/test/java` and `build.gradle`


### Running unit tests

    ./gradlew test
    
### Running integration tests

    ./gradlew integrationTest