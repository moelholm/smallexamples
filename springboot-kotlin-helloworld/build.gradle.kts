
val springBootVersion = "1.5.2.RELEASE"
val kotlinVersion = "1.1.0"

buildscript {
    val springBootVersion = "1.5.2.RELEASE"
    val kotlinVersion = "1.1.0"
    repositories {
        gradleScriptKotlin()
    }
    dependencies {
//        classpath(kotlinModule("gradle-plugin"))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }
}

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
}

repositories {
    gradleScriptKotlin()
}

//sourceCompatibility = 1.8
//targetCompatibility = 1.8
dependencies {
    compile(kotlinModule("stdlib"))
    compile("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    testCompile("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

