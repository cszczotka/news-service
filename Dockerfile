FROM openjdk:11-jre

RUN sbt dist
RUN unzip -d svc target/universal/*-1.0-SNAPSHOT.zip
RUN mv svc/*/* svc/
RUN mv svc/bin/* svc/bin/start

COPY svc /svc
EXPOSE 9000 9443
CMD /svc/bin/start -Dhttps.port=9443 -Dplay.crypto.secret=secret