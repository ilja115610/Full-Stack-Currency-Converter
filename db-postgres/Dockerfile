FROM postgres:12
ENV POSTGRES_DB=currency_exchange
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres03
RUN su postgres
COPY script.sql /docker-entrypoint-initdb.d/

