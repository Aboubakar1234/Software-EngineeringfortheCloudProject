# Utilise une image OpenJDK pour faire tourner l’app Spring Boot
FROM openjdk:17-jdk-slim

# Copie du jar généré dans l'image
COPY target/sa-backend-0.0.1-SNAPSHOT.jar /app.jar

# Indique le port que l'appli écoute
EXPOSE 8080

# Commande par défaut au démarrage du conteneur
ENTRYPOINT ["java", "-jar", "/app.jar"]
