FROM registry.redhat.io/rhel8/postgresql-12@sha256:b3e5b7bc6acd6422f928242d026171bcbed40ab644a2524c84e8ccb4b1ac48ff
ENV POSTGRESQL_USER=postgres
ENV POSTGRESQL_PASSWORD=postgres03
ENV POSTGRESQL_DATABASE=currency_exchange
RUN chgrp -R 0 /var/lib/pgsql/data && \
    chmod -R g=u /var/lib/pgsql/data
COPY script.sql /docker-entrypoint-initdb.d/
