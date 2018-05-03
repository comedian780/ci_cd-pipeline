FROM openjdk:alpine

MAINTAINER AaronLuellwitz <aaron.luellwitz@gmx.de>

RUN mkdir APP

COPY . APP/.
WORKDIR APP

ENV LANG C.UTF-8

RUN ls -al ./build/libs/
