FROM openjdk:16.0.1-slim-buster

COPY . .

RUN npm install

CMD [ "npm", "start" ]


