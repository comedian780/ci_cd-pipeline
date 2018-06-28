FROM nginx:alpine

MAINTAINER AaronLuellwitz <aaron.luellwitz@gmx.de>

ENV LANG C.UTF-8

ADD . /usr/share/nginx/html/ui/.
RUN rm /usr/share/nginx/html/ui/Dockerfile
RUN rm /usr/share/nginx/html/index.html

EXPOSE 80
