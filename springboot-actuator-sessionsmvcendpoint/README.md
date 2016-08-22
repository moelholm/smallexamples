# Spring Boot 1.4 :: SessionsMvcEndpoint Actuator Endpoint

This example includes a Spring Boot Actuator endpoint that exposes the currently active HttpSessions.

## Try it out
Run:
 
    ./gradlew clean bootRun 

Open a browser at: http://localhost:8080/actuator/sessions
 
Notice that there are no active sessions. Let's change that:

Open a new browser window/tab at: http://localhost:8080/greetings/duke

Repeat the process - _open another browser window in private mode_. 

Refresh: http://localhost:8080/actuator/sessions
 
Notice that it shows the user sessions.

The information you've just seen comes from a custom Spring Boot Actuator HTTP endpoint. That's easy to develop: checkout the source code.