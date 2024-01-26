# Utiliza una imagen base con Java 8 o superior
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR desde la carpeta target
COPY target/ms-seguridad-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 500 (ajusta según sea necesario)
EXPOSE 500

# Comando para ejecutar la aplicación cuando el contenedor se inicia
CMD ["java", "-jar", "app.jar"]
