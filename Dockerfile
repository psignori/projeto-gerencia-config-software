FROM openjdk:16.0.1-slim-buster

WORKDIR /bin/bash

COPY . .

RUN npm install

CMD [ "npm", "start" ]



