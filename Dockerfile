FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD  search-v1.jar /app.jar
EXPOSE  8888
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
