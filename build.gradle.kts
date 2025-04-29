val appVersion: String by project
val liquibaseVersion: String by project
val jwtVersion: String by project
val openapiVersion: String by project

plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.kentagon"
version = appVersion

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	implementation("org.postgresql:postgresql")
	implementation("org.liquibase:liquibase-core:$liquibaseVersion")

	implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openapiVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
