# Spring Boot + Gradle : Category based test separation

This example illustrates a simple approach to partition your tests into _unit tests_ and _integration tests_.

This approach:
- allows you to keep all tests in the same source directory `src/test/java`
- is based on JUnit categories
- uses Gradle to partition the tests using JUnit categories

See `src/test/java` and `build.gradle`


### Running unit tests

    ./gradlew test
    
### Running integration tests

    ./gradlew integrationTest