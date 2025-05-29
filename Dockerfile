# Step 1: Use a Java base image
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy jar (you can update the filename accordingly)
COPY target/library-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port
EXPOSE 8080

# Step 5: Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
