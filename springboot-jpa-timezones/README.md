# Spring Boot and JPA: Controlling time zones

This example shows how you can control the time zone used when saving/loading "temporal" JPA entity properties.

More specifically it illustrates how to use Hibernate specific configuration to force dates to be serialized in UTC/GMT.

This is an alternative to putting the entire JVM into UTC/GMT ...fx via `TimeZone.setDefault(TimeZone.getTimeZone("GMT"));` or via the commandline switch `-Duser.timezone=GMT`.

Technical details: Spring Boot 1.4.2, JPA 2.1, Hibernate 5.2.3

### Run

Launch database with docker

    cd src/database
    
    ./docker-compose up -d
    
    ( ^ db now runs at localhost:3306 || access with root/secret ^ )
    
Run tests

    ./gradlew test
    
    

### About
    
Look at `build.gradle`. Notice how Hibernate is upgraded to v5.2.3.Final.

Look at `src/main/resources/application.yml`.
 - Notice  `spring.jpa.properties.hibernate.jdbc.time_zone:UTC`. This is how we instruct Hibernate to serialize timestamps into UTC.
 - Notice `?useLegacyDatetimeCode=false` in the database URL. This is IMPORTANT if you want the Hibernate configuration to work.
 
So basically we use Hibernate specific configuration to force dates to be serialized in UTC/GMT.
 
Look at `src/main/java/com/moelholm/JpaIntegrationTests.java`. This class contains a test that documents the effect of the Hibernate UTC/GMT configuration.
 
Look at `src/main/java/com/moelholm/JdbcIntegrationTests.java`. This class contains a test that illustrates what Hibernate does under the cover: Effectively passing a timezone aware `Calendar` instance to the underlying JDBC API. 

*TIP:* If you use MariaDB (like this example) or MySQL then you could also just append `&serverTimezone=GMT` to the database URL. In other words: You really don't need to use the Hibernate specific timezone configuration.
