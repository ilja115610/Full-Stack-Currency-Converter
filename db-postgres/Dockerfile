FROM postgres:12
ENV POSTGRES_DB=currency_exchange
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres03
RUN mkdir /storageData
ENV PGDATA=/storageData
RUN chown -R "$(id -u)" "$PGDATA" 2>/dev/null || :
RUN chmod -R 777 /storageData
COPY script.sql /docker-entrypoint-initdb.d/

