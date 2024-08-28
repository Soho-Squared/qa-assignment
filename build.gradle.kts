group = "com.sohosquared"
version = "0.0.1-SNAPSHOT"
description = "qa-assignment"
java.sourceCompatibility = JavaVersion.VERSION_21

plugins {
	java
	`java-library`
	jacoco
	`project-report`
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

val springdocOpenapiStarterWebfluxUiVersion = "2.3.0"
val swaggerAnnotationsVersion = "2.2.15"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	api("org.springdoc:springdoc-openapi-starter-webflux-ui:$springdocOpenapiStarterWebfluxUiVersion")
	api("org.springframework.boot:spring-boot-starter-webflux")
	annotationProcessor("io.swagger.core.v3:swagger-annotations:$swaggerAnnotationsVersion")
	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport)
	useJUnitPlatform()
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
	}
	dependsOn(tasks.test)
}