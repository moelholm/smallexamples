# Spring Boot + Gradle : @IfProfileValue based test separation

This example illustrates a simple approach to partition your tests into _unit tests_ and _integration tests_.

This approach:
- allows you to keep all tests in the same source directory `src/test/java`
- is annotation based: annotate unit tests with `@UnitTest` and integration tests with `@IntegrationTest`
- uses Gradle to select which "profile" to run (unit tests or integration tests)

See `src/test/java` and `build.gradle`


### Running unit tests

    ./gradlew test
    
### Running integration tests

    ./gradlew integrationTest