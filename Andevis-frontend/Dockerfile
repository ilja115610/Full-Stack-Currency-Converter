FROM registry.redhat.io/rhel8/nodejs-12@sha256:af4fbb0820f08ce89d896f2eff6148b9c0e5a28cef3f809341ebc18cd48ad2ad AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY --from=build /app/dist/Andevis-frontend /usr/share/nginx/html
EXPOSE 80
