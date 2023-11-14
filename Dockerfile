FROM maven:latest AS stage1
WORKDIR /dispatcher
COPY pom.xml /dispatcher
RUN mvn dependency:resolve
COPY . /dispatcher
RUN mvn clean install
RUN mvn package -DskipTests

FROM openjdk:17 as final
COPY --from=stage1 /dispatcher/target/*.jar dispatcher.jar
EXPOSE 8080
CMD ["java", "-jar", "dispatcher.jar"]