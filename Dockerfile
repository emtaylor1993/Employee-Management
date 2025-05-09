FROM maven:3.6.3-openjdk-17-slim
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
EXPOSE 8080
WORKDIR /app/target
CMD ["java", "-jar", "employee-management-0.0.1-SNAPSHOT.jar"]