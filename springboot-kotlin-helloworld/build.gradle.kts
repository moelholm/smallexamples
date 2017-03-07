buildscript {

    // 1 of 2: Define properties here...
    var springBootVersion: String by extra
    springBootVersion = "1.5.2.RELEASE"

    var kotlinVersion: String by extra
    kotlinVersion = "1.1.0"

    repositories {
        gradleScriptKotlin()

    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }

}

// 2 of 2: Bring properties into scope again here...
val kotlinVersion: String by extra
val springBootVersion: String by extra

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
}

repositories {
    gradleScriptKotlin()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    compile("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    testCompile("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}
