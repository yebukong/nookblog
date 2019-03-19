FROM sapmachine/jdk10
VOLUME /tmp
ADD nookblog-0.8.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]