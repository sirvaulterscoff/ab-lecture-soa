FROM openjdk:17-slim

COPY build/libs/ab-lecture-soa-0.0.1-SNAPSHOT.jar /opt/ab.jar

CMD ["java", "-jar", "/opt/ab.jar"]