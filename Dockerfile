# Этап сборки
FROM gradle:8-jdk21 as builder

WORKDIR /app

# Сначала копируем только файлы Gradle, выполняем сборку. Сборка не удастся, зато зависимости скачаются. В дальнейшем эти два этапа закешируются.
COPY build.gradle.kts gradle.properties settings.gradle.kts /app
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

# Копируем остальные файлы (кроме тех, что указаны в .dockerignore)
COPY . /app
RUN rm -f build/libs/*.jar
RUN gradle build -x test --no-daemon
# Небольшой хак, чтобы не пришлось менять Dockerfile с каждой новой версией сервера
RUN rm -f build/libs/*-plain.jar
RUN mv build/libs/dm-*.jar build/libs/dm.jar

# Непосредственно запускаемый контейнер, берёт "толстый" JAR с предыдущего этапа
FROM openjdk:21-slim

WORKDIR /app

COPY --from=builder /app/build/libs/dm.jar dm.jar
COPY --from=builder /app/config/dm-backend/*.yml .

EXPOSE 8080

CMD ["java", "-jar", "dm.jar", "--spring.config.location=file:./application.yml"]
