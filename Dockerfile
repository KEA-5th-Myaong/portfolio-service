FROM openjdk:17
COPY build/libs/popolog-portfolio-service.jar popolog-portfolio-service.jar
ENTRYPOINT ["java", "-jar", "/popolog-portfolio-service.jar"]