FROM openjdk:11-jre
COPY target/universal/news-service-1.0-SNAPSHOT.zip .
RUN set -eux; apt-get update; apt-get install unzip
RUN unzip news-service-1.0-SNAPSHOT.zip -d svc
RUN mv svc/*/* svc/
RUN rm svc/news-service-1.0-SNAPSHOT.zip
EXPOSE 9000 9443
CMD /svc/bin/news-service -Dhttps.port=9443 -Dplay.http.secret.key='QCY?tAnfk?aZ?iwrNwnxIlR6CTf:G3gf:90Latabg@5241AB`R5W:1uDFN];Ik@n'
#ENTRYPOINT ["tail", "-f", "/dev/null"]