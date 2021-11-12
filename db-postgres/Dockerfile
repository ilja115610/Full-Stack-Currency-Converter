FROM postgres:12
ENV POSTGRES_DB=currency_exchange
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres03
USER ${POSTGRES_UID}:${POSTGRES_GID}
RUN chgrp -R 0 /var/lib/postgresql/data && \
    chmod -R g=u /var/lib/postgresql/data
COPY script.sql /docker-entrypoint-initdb.d/

