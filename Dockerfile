FROM openjdk:18-jdk-alpine as builder

WORKDIR /app/super_coffe

COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:18-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/super_coffe/target/super_coffee_rest_api-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8080
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java", "-jar", "super_coffee_rest_api-0.0.1-SNAPSHOT.jar"]