FROM node:20-alpine

WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./
RUN npm install

# Copiar código fuente
COPY . .

# Exponer puerto
EXPOSE 4200

# Ejecutar en modo desarrollo
CMD ["npm", "start"]
