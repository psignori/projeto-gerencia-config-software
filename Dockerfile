FROM ubuntu:20.04

COPY . .

RUN npm install

CMD [ "npm", "start" ]


