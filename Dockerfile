FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

RUN apk add --no-cache maven

RUN mvn dependency:go-offline -B

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/appForCalculatingNetLoad-0.0.1-SNAPSHOT.jar"]