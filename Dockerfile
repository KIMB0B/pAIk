# 1. Base image로 OpenJDK를 사용
FROM openjdk:17-jdk-slim

# 2. JAR 파일을 애플리케이션 디렉터리에 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. 컨테이너가 시작될 때 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]