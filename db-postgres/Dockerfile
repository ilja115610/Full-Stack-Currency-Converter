FROM postgres:12
RUN /bin/bash -c 'chmod -R 777 /var/lib/postgresql/data'
ENV POSTGRES_DB=currency_exchange
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres03
COPY script.sql /docker-entrypoint-initdb.d/

