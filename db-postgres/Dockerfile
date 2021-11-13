FROM registry.access.redhat.com/openshift3/postgresql-92-rhel7
ENV POSTGRESQL_USER=postgres
ENV POSTGRESQL_PASSWORD=postgres03
ENV POSTGRESQL_DATABASE=currency_exchange
RUN chgrp -R 0 /var/lib/pgsql/data && \
    chmod -R g=u /var/lib/pgsql/data
COPY script.sql /docker-entrypoint-initdb.d/
