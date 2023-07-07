FROM alpine:3.17.2
RUN apk add --no-cache openjdk17
COPY build/libs/ms-upgate-integration.jar /app/
ENTRYPOINT ["java"]
CMD ["-jar", "app/ms-upgate-integration.jar"]