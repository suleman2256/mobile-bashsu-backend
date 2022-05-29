FROM maven:3.8.2-openjdk-11 as build

COPY ../.. /usr/src/myapp
WORKDIR /usr/src/myapp

RUN mvn clean package -DskipTests -e

FROM openjdk:11

ARG JAVA_PARAM="-Xms256M -Xmx1024M -XX:+UseG1GC"
ENV JAVA_PARAM=$JAVA_PARAM

WORKDIR /opt/app

COPY / /opt/app

COPY --from=build /usr/src/myapp/target/*.jar /opt/app/app.jar

EXPOSE 8091

CMD ["sh", "-c", "java ${JAVA_PARAM} -Dserver.port=8091 -Dfile.encoding=UTF-8 -jar /opt/app/app.jar"]