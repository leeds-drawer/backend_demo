# 1) 빌드 스테이지: Gradle로 JAR 생성
FROM gradle:8-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle clean bootJar --no-daemon

# 2) 런타임 스테이지: 생성된 JAR만 가져와서 실행
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 여기를 와일드카드로 바꿔서, 버전이 바뀌어도 동작하게끔 합니다.
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
