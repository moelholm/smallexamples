# Spring Boot :: Custom Spring Boot Actuator endpoints

This example show how you can add custom Spring Boot application insight: As endpoints to the Spring Boot Actuator system.

## Try it out
Run:
 
    ./gradlew clean bootRun 

Open a browser at: http://localhost:8080/actuator/usersessions
 
Notice that there are no active sessions. Let's change that:

Open a new browser window/tab at: http://localhost:8080/greetings/java
 
You'll be prompted for a username / password. Provide: username=`duke`,  password=`duke`

Repeat the process - _open another browser window in private mode_. Provide: username=`tux`,  password=`tux`

Refresh: http://localhost:8080/actuator/usersessions
 
Notice that it shows the user sessions.

The information you've just seen comes from a custom Spring Boot Actuator HTTP endpoint. That's easy to develop: checkout the source code.