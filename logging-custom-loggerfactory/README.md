# Custom logger factory

Have you ever copy/pasted a class - and then forgot to change the class passed to the logger instance? 

This example shows, using SLF4J/Logback, how you can create a custom LoggerFactory that automatically infers the logger name:

    private static final Logger LOG = MyLoggerFactory.getLogger();

Notice how there is no argument to the method.