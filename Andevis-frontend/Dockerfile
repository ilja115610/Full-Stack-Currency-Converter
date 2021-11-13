FROM node:14.15 AS build
USER root
WORKDIR /app
COPY . .
RUN npm install && \
    npm run build --async false
### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY --from=build /app/dist/Andevis-frontend /usr/share/nginx/html
EXPOSE 80
