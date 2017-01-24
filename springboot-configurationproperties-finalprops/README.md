# Spring Boot :: Configuration properties (final members)

### How to run

    export MY_MESSAGES="a,b,c"
    
    ./gradlew test
    
### What to conclude (if anything)

If you run `ConfigurationPropertiesTests` you will see that `testFinalProperties` fails - since it doesn't pick up the OS environment variable you configured before the tests was run.

The final `List` member in `FinalProperties` behaves different than for the `NormalProperties`.

Probably works as expected...