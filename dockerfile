FROM amd64/amazoncorretto:17
WORKDIR /app
COPY ./build/libs/docker-0.0.1-SNAPSHOT.jar /app/NOW-SOPT.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "NOW-SOPT.jar"]
