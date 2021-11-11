FROM node:12.14-alpine AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY --from=build /app/dist/Andevis-frontend /usr/share/nginx/html
