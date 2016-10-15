# Spring Boot 1.4 BDD testing

This project shows how you can implement BDD tests with Spring Boot.

### Inspect
Checkout the Gherkin tests here:

    ./src/cucumber/resources/greetingsResource.feature

See the steps implementations here:

    ./src/cucumber/java/com/moelholm/cucumber/GreetingsResourceSteps.java

Curious about the RESTful(ish) resource? See it here:

    ./src/main/java/com/moelholm/cucumber/GreetingsController.java

### Run
Run the cucumber tests like this:

    ./gradlew clean cucumber
    
Observe the console for test result. Also find cucumber test reports in directory `build/cucumber`.