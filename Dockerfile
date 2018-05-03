#FROM alpine:latest
FROM openjdk:alpine

MAINTAINER AaronLuellwitz <aaron.luellwitz@gmx.de>

RUN mkdir APP

COPY . APP/.
WORKDIR APP

ENV LANG C.UTF-8



# RUN java -jar ./build/libs/ci_cd-pipeline.jar
 RUN ls -al
# RUN ./start.sh
