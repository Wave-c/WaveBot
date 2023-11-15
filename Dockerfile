FROM maven:latest AS stage1
WORKDIR /WaveBot
COPY pom.xml /WaveBot
RUN mvn dependency:resolve
COPY . /WaveBot
RUN mvn clean install
RUN mvn package -DskipTests

FROM openjdk:17 as final
COPY --from=stage1 /WaveBot/target/*.jar WaveBot.jar
EXPOSE 8080
CMD ["java", "-jar", "WaveBot.jar"]