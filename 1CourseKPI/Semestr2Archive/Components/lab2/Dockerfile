FROM nginx:1.19-alpine

RUN apk update && \
apk add tzdata && \
cp /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
RUN apk add vim

ADD index.tar.gz /usr/share/nginx/html/

ENV TZ=America/Los_Angeles

EXPOSE 8012
