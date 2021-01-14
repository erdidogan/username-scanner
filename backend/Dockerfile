FROM openjdk:16-jdk-alpine3.12
MAINTAINER Erdi Dogan <erdidogandev@gamil.com>
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
ADD target/${JAR_FILE} app.jar
CMD java -Dserver.port=$PORT $JAVA_OPTS -jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

