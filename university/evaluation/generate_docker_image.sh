./mvnw package -DskipTests
docker build -t evaluation .
rm ./target/evaluation-0.0.1-SNAPSHOT.jar
