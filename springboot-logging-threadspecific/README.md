# Spring Boot 1.4 :: Log everything for selected Web requests

Have you ever tried to operate an application in production where the loglevel is INFO or above? 
 
And have you ever wished that you could see everything below INFO for one Web request only?

This example shows how you can do that using Spring Boot and Logback. 


## Try it out
Run:
 
    ./gradlew clean bootRun 

Open a browser at: http://localhost:8080/greetings/duke

Notice that you only see INFO log statements and above.

Open a browser at: http://localhost:8080/greetings/duke?trace=on

( notice the trace query parameter - and the value on )

Again, open a browser at: http://localhost:8080/greetings/duke
 
Notice that you now see EVERY log statements on EVERY category.
