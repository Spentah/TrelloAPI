FROM maven:3.8.4-openjdk-8
COPY ./ ./
RUN mvn clean package
CMD ["java", "-jar", "target/IPR_Twitter-1.0-SNAPSHOT"]