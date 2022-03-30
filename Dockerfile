FROM maven:3.8.4-openjdk-8
COPY target/IPR_Twitter-1.0-SNAPSHOT.jar /trello.jar
CMD ["java", "-jar", "/trello.jar"]