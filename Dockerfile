FROM openjdk:17-oracle
ADD target/teleservice-0.0.1-SNAPSHOT.jar teleservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "teleservice-0.0.1-SNAPSHOT.jar"]


# docker build -t teleservice:<tagname> .
# tagname can be anything...here it is version 0.0.1
# docker images
# docker run -d -p 9229:9229 teleservice

# docker tag 978a73d1af89 samyukthakirankumar/practice:teleservice
# <image_id> <username>/<repository>:<tagname>
# docker push samyukthakirankumar/practice:teleservice
# <username>/<repository>:<tagname>
# docker pull samyukthakirankumar/practice:teleservice
# docker run -d -p 9229:9229 <image_id>