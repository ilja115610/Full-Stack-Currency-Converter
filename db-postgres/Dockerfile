FROM rhel8/postgresql-12
ENV POSTGRESQL_USER=postgres
ENV POSTGRESQL_PASSWORD=postgres03
ENV POSTGRESQL_DATABASE=currency_exchange
RUN chgrp -R 0 /var/lib/pgsql/data && \
    chmod -R g=u /var/lib/pgsql/data
COPY script.sql /docker-entrypoint-initdb.d/
