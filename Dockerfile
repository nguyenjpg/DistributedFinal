FROM openjdk:21-jdk-slim
WORKDIR /var/www/html
COPY . . 
RUN javac masterServer.java
EXPOSE 8000
CMD ["java", "masterServer"]