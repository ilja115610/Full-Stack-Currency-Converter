FROM registry.access.redhat.com/openshift3/postgresql-92-rhel7
ENV POSTGRESQL_USER=ilja115610
ENV POSTGRESQL_PASSWORD=postgres03
ENV POSTGRESQL_DATABASE=currency_exchange
COPY script.sql /docker-entrypoint-initdb.d/
