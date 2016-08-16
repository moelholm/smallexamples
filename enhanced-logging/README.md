# Spring Boot :: Enhanced logging information

This example illustrates how you can add additional information to the log output from your Spring Boot application.

The additional information, fx username and requestid, will show up in all log lines _without_ you having to prepend it from your "normal" log lines.

This kind of omnipresent data is called MDC data (Mapped Diagnostic Context). You'll find support for that in the most popular Java logging frameworks. 

In this example, the MDC data is based on logback and activated in:

    src/main/resources/application.properties
    
    and
    
    src/main/java/com/moelholm/RequestFilter.java

## Try it out
Run:
 
    ./gradlew clean bootRun 

Open a browser at: http://localhost:8080/greetings/duke 

Observe the log output: It captures a request id and a user id _and_ it is added automatically to all loglines emitted during the HTTP request.

 
