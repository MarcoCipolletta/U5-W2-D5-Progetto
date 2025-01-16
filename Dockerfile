# Usa un'immagine base con OpenJDK
FROM openjdk:17-jdk-slim

# Aggiungi un volume temporaneo
VOLUME /tmp

# Argomento per specificare il file JAR
ARG JAR_FILE=target/*.jar

# Copia il file JAR generato nella directory del container
COPY ${JAR_FILE} app.jar

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "/app.jar"]
