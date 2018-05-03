FROM centos:7
ENV RELEASE_PATH target/beer-api-swarm.jar
RUN yum install -y \
    java-1.8.0-openjdk-headless \
  && yum clean all

WORKDIR /opt/beer-api

ADD ${RELEASE_PATH} /opt/beer-api

RUN groupadd -r duser -g 1001 \
    && useradd -u 1001 -r -g duser -d /opt/beer-api/ -s /sbin/nologin -c "Docker image user" duser \
    && chown -R duser:duser /opt/beer-api/ \
    && chgrp -R 0 /opt/beer-api && chmod -R g=u /opt/beer-api

USER 1001


EXPOSE 8080

ENV PORT_OFFSET=0
ENV LOGGING_LEVEL=INFO

CMD java -jar /opt/beer-api/beer-api-swarm.jar \
    -Dswarm.port.offset=${PORT_OFFSET} \
    -Dswarm.logging=${LOGGING_LEVEL}
