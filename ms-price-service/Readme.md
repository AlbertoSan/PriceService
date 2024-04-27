# Prueba Técnica Precios Text

## Descripción General

El objetivo de esta prueba es construir una API en Spring Boot para calcular el precio final de un producto en una cadena de tiendas para una fecha específica. El proyecto utiliza las siguientes tecnologías y enfoques:

- Spring Boot
- H2 Database
- Docker
- SLF4J para Logging
- Gherkin y BDD
- Postman
- (Cualquier otra tecnología o característica relevante)

---

## 🛠️ Instalación y Configuración

### 📋 Requisitos Previos

- JDK 11 o superior
- Maven (Opcional, si no usas el wrapper de Maven incluido)
- Docker (Opcional, para ejecución en contenedor)

### 🚀 Pasos para la Instalación y Ejecución

1. **Navegar al Directorio del Proyecto**
    ```bash
    cd directorio_del_proyecto
    ```

2. **Construir y Empaquetar con Maven**
    - Usando Maven instalado:
        ```bash
        mvn clean install
        ```
    - Usando el Wrapper de Maven:
        ```bash
        ./mvnw clean install
        ```

3. **Ejecutar el Proyecto**
    - Con el JAR generado:
        ```bash
        java -jar target/prueba-tecnica-0.0.1-SNAPSHOT.jar
        ```
    - Usando Docker:
        ```bash
        docker run -p 8080:8080 -t prueba-tecnica:0.0.1-SNAPSHOT
        ```
    - Usando Docker Compose:
        ```bash
        docker-compose up
        ```

4. **Ejecutar las Pruebas**
    ```bash
    mvn test
    ```

---

Este README debe proporcionar toda la información necesaria para que cualquier desarrollador pueda instalar y ejecutar el proyecto fácilmente.

