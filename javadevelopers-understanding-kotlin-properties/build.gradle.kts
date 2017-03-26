import org.jetbrains.kotlin.utils.addToStdlib.cast
import java.io.File

buildscript {
  val kotlinVersion = "1.1.1"

  extra["kotlinVersion"] = kotlinVersion

  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
  }
}

apply {
  plugin("kotlin")
  plugin("java")
}

repositories {
  mavenCentral()
}

val kotlinVersion = extra["kotlinVersion"]

dependencies {
  compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
  compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
  testCompile("junit:junit:4.12")
  testCompile("org.assertj:assertj-core:3.6.2")
}

task("javap") {
  doLast {
    val property = "file"
    val fileName = properties.getOrElse(property) { "" }.toString()
    File("${buildDir.path}/classes/main/").walk()
            .filter { it.name.endsWith(".class") && it.name.contains(fileName, true) }
            .map { it.path }
            .forEach {
              exec {
                executable("javap")
                args("-p", it)
              }
            }
  }

}