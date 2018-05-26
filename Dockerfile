# Buildstage
FROM gradle:jdk-alpine AS BUILD_IMAGE

MAINTAINER AaronLuellwitz <aaron.luellwitz@gmx.de>

ADD --chown=gradle . /APP
WORKDIR /APP

RUN gradle clean build

# Stage for production use
FROM openjdk:jre-alpine

RUN mkdir /APP
WORKDIR /APP

COPY --from=BUILD_IMAGE /APP/build/libs/. /APP
COPY --from=BUILD_IMAGE /APP/start.sh /APP

CMD ./start.sh
