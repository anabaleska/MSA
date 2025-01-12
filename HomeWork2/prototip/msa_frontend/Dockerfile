FROM node:16
WORKDIR /frontend
COPY package.json /frontend/package.json
RUN npm install
COPY . /frontend
RUN npm run build
RUN npm install -g serve
EXPOSE 3000
CMD ["serve", "-s", "build", "-l", "3000"]