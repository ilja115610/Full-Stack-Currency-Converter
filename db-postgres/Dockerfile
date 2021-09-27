FROM postgres:12
ENV POSTGRES_PASSWORD postgres03
ENV POSTGRES_DB currency_exchange
VOLUME ["/vol"]
COPY script.sql /docker-entrypoint-initdb.d/
