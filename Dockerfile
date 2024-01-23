FROM openjdk:17-jdk-slim
LABEL authors="nitishku"

WORKDIR /app

ADD ./build/libs/Spring-HzCast-1.0.0.jar .

EXPOSE 8080 5701

CMD ["java", "-jar", "Spring-HzCast-1.0.0.jar"]