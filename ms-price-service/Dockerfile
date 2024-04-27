# Utilizar una imagen base de OpenJDK
FROM openjdk:11-jre-slim

# Definir el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR en el contenedor
COPY target/ms-price-service.jar /app/ms-price-service.jar

# Definir variables de entorno, si es necesario
# ENV SOME_VARIABLE=some_value

# Ejecutar la aplicación
CMD ["java", "-jar", "/app/ms-price-service.jar"]