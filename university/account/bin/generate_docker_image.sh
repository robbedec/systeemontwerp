./mvnw package -DskipTests
docker build -t account_service .
rm ./target/account-0.0.1-SNAPSHOT.jar
