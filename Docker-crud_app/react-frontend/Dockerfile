FROM node:16-alpine 
WORKDIR /app

COPY . .

RUN npm i 
RUN npm run build

ENV NODE_ENV production

CMD [ "npx", "serve", "build" ]