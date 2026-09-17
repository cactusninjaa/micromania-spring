# ---------- Etage 1 : compilation ----------
# Maven et le JDK ne servent qu'ici, ils ne finiront pas dans l'image finale.
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /build

# Le pom seul d'abord : tant qu'il ne change pas, Docker reutilise le cache
# de cette couche et ne retelecharge pas les dependances a chaque build.
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Puis les sources, qui changent a chaque modification
COPY src ./src
# -Dmaven.test.skip : on ne compile ni n'execute les tests dans l'image
RUN mvn -B clean package -Dmaven.test.skip=true

# ---------- Etage 2 : execution ----------
# On repart d'une image sans Maven : seul le jar est recopie.
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /build/target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/app/application.jar"]
