FROM adoptopenjdk/openjdk11
ADD target/*.jar EDMS_REST_API-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "EDMS_REST_API-0.0.1-SNAPSHOT.jar"]

