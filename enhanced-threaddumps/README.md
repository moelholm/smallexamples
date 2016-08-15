# Spring Boot :: Enhanced threaddump information

This example illustrates how you can add additional information to the threads in your Spring Boot application.

The additional information will show up in any thread dump you make. That applies to dumps from the '/dump' actuator endpoint as well as any dumps you take by other means.

## Try it out
Run:
 
    ./gradlew clean bootRun 

Open a browser at: http://localhost:8080/slowgreetings/duke 

Open a browser at: http://localhost:8080/dump 

( you have less than 10 seconds to grab the dump before the previous request finishes )

Search for "duke" in the output. 

Notice the extra information in the thread name. 
