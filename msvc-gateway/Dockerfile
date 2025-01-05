FROM openjdk:17-jdk-alpine AS builder

WORKDIR /app/msvc-gateway

COPY ./pom.xml /app
COPY ./msvc-gateway/.mvn ./.mvn
COPY ./msvc-gateway/mvnw .
COPY ./msvc-gateway/pom.xml .

#esto carga las dependencias
RUN  ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-gateway/src ./src

#genera el jar dentro del target
RUN  ./mvnw clean package -DskipTests

#obtenemos el jar generado arriba y la ponemos aqui y generamos la imagen recien , esto gasta menos recursos
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-gateway/target/msvc-gateway-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8001

ENV PORT=$PORT_APP

EXPOSE $PORT

#ENTRYPOINT ["java","-jar","msvc-usuarios-0.0.1-SNAPSHOT.jar"]
#lo mismo que el entrypoint pero con CMD podemos entrar a la consola de la imagen
CMD ["java","-jar","msvc-gateway-0.0.1-SNAPSHOT.jar"]