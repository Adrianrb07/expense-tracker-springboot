# ---------- Stage 1: Build ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos Gradle wrapper y descriptores primero para cachear deps
COPY gradlew gradlew.bat ./
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Descarga dependencias (sin compilar aún)
RUN ./gradlew --no-daemon dependencies || true

# Copiamos el código fuente
COPY src src

# Compila y empaqueta (jar en build/libs/*.jar)
RUN ./gradlew --no-daemon clean bootJar

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Carpeta para datos (si usas H2 en archivo)
VOLUME ["/app/data"]

# Copiamos el jar construido
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto interno del contenedor (tu app puede seguir en el puerto configurado [8081])
EXPOSE 8080

# Activa un perfil específico para Docker
ENV SPRING_PROFILES_ACTIVE=docker

# Ejecuta la app
ENTRYPOINT ["java","-jar","/app/app.jar"]
