plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.jsomsouk.tennis.kata"
version = "0.0.1-SNAPSHOT"
description = "Kata demo tennis game"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.assertj:assertj-core")
	testImplementation("org.mockito:mockito-junit-jupiter")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<JavaExec>("runKata") {
	group = "application"
	description = "Runs the simple tennis kata implementation"

	classpath = sourceSets.main.get().runtimeClasspath
	mainClass.set("com.jsomsouk.tennis.kata.GameSetMatch.SimpleGame")

	// Default argument if none provided
	args = listOf("AABBAA")

	// Allow overriding with -PkataArgs="ABABAB"
	if (project.hasProperty("kataArgs")) {
		args = project.property("kataArgs").toString().split(" ")
	}
}
