FROM node:19-alpine AS development

WORKDIR /app/practice-ui
COPY package*.json ./

RUN npm install -g @angular/cli
RUN npm install
COPY . .
EXPOSE 4200
CMD ["npm", "start"]