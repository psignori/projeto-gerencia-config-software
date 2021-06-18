FROM homologacao:1.0

COPY . .

RUN jar build Main.jar

EXPOSE 8080

ENTRYPOINT [ "./Main" ]
