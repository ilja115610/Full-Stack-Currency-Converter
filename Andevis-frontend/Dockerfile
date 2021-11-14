FROM node:14.15 AS build
USER root
WORKDIR /app
RUN chgrp -R 0 /app && \
    chmod -R g=u /app
COPY dist ./
### STAGE 2: Run ###
FROM twalter/openshift-nginx:latest
COPY ./dist/Andevis-frontend /usr/share/nginx/html
EXPOSE 8081

