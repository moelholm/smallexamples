# A simple CDI profiling interceptor

Allows you to apply simple profiling to EJB's and CDI beans. 

You can get output such as:

    ------------------------------------------------------------------------------------
    PresentationGreetingBean
    ------------------------------------------------------------------------------------
     1451 ms - PresentationGreetingBean.sayHello()
         1225 ms - BusinessServiceGreetingBean.sayHello()
              305 ms - GreetingRepositoryBean.getGreeting()
                2 ms - ?
              305 ms - GreetingRepositoryBean.getGreeting()
                1 ms - ?
              305 ms - GreetingRepositoryBean.getGreeting()
                2 ms - ?
              303 ms - GreetingRepositoryBean.getGreeting()
          181 ms - ?
           41 ms - AuditLoggerBean.auditLogStuff()
    ------------------------------------------------------------------------------------

Can be useful if you don't have lightwight profiling enabled via a Java agent technology. 

The above output can be directed into a log file upon selected thresholds. For example, if a request takes more than 5 seconds - then, and only then, dump it's callchain (the above) to the log file.