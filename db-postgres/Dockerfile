FROM postgres:12
RUN chown -R $(id -u) $PGDATA 2>/dev/null || :
ENV POSTGRES_DB=currency_exchange
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres03
COPY script.sql /docker-entrypoint-initdb.d/

