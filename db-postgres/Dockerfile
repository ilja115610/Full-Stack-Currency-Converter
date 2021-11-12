FROM postgres:12
RUN /bin/bash -c 'chmod 777 /var/lib/postgresql/data'
RUN /bin/bash -c 'chmod 777 /var/lib/postgresql/data/pgdata'
ENV POSTGRES_DB=currency_exchange
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres03
COPY script.sql /docker-entrypoint-initdb.d/

