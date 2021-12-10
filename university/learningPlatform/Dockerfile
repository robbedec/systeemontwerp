FROM openjdk:11
COPY . .
RUN apt-get install -y curl && ./mvnw package -DskipTests
CMD ["java", "-jar", "./target/learningplatform-0.0.1-SNAPSHOT.jar"]
