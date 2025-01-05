FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-habitaciones

COPY ./pom.xml /app
COPY ./msvc-habitaciones/.mvn ./.mvn
COPY ./msvc-habitaciones/mvnw .
COPY ./msvc-habitaciones/pom.xml .

#esto carga las dependencias
RUN  ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-habitaciones/src ./src

#genera el jar dentro del target
RUN  ./mvnw clean package -DskipTests

#obtenemos el jar generado arriba y la ponemos aqui y generamos la imagen recien , esto gasta menos recursos
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-habitaciones/target/msvc-habitaciones-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8003

ENV PORT $PORT_APP

EXPOSE $PORT

CMD ["java","-jar","msvc-habitaciones-0.0.1-SNAPSHOT.jar"]