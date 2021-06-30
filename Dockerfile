FROM homologacao:1.0

COPY . .

RUN apt install vim

CMD [ "y" ]


