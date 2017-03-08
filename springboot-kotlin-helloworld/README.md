# Spring Boot 1.5 :: Using Kotlin for the build n' code
 
This example shows how you can create a Spring Boot 1.5 project that is based on Kotlin 1.1 for the application code as well as the Gradle script. 

### Kotlin in the Code



### Kotlin in the Gradle build script

Kotlin as the language in Gradle is still work in progress. But it is definitely in a state where you can take it for a test spin.

Firstly, if you want up-to-date IDE support - then make sure you are using IntelliJ (update it).

Secondly, notice that this project has had a special Gradle wrapper generated. These are the steps: 

0. Source - see: 
    
       https://github.com/gradle/gradle-script-kotlin/releases

1. Locate the latest and greatest distro (those ending with `-all.zip`)
       
       https://repo.gradle.org/gradle/dist-snapshots/
 
2. Generate a Kotlin aware Gradle wrapper
       
       gradle wrapper --gradle-distribution-url https://repo.gradle.org/gradle/dist-snapshots/gradle-script-kotlin-YOURVERSIONHERE-all.zip
     
     
    
