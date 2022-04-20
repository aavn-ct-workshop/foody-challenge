FROM node:14-alpine3.13 as build
WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./
RUN ls -l

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

RUN npm install -g http-server

EXPOSE 8080

CMD [ "http-server", "dist", "--port 8080" ]