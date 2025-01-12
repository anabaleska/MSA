FROM node:16

# Set the working directory
WORKDIR /frontend

# Copy package.json and package-lock.json first to install dependencies
COPY package.json package-lock.json /frontend/

# Clean npm cache and install dependencies, using --legacy-peer-deps to avoid peer dependency issues
RUN npm cache clean --force && npm install --legacy-peer-deps

# Install babel plugin to fix missing plugin issue
#RUN npm install --save-dev @babel/plugin-proposal-private-property-in-object
#
## Install ESLint configuration dependencies to fix conflicts
#RUN npm install --save-dev eslint-config-react-app

# Check if react-scripts is installed correctly
RUN ls -al /frontend/node_modules/react-scripts

# If react-scripts is not found, install it globally
RUN npm install -g react-scripts

# Copy the rest of the application code
COPY . /frontend/

# Build the React app
RUN npm run build

# Install serve globally to serve the production build
RUN npm install -g serve

# Expose the port for the app
EXPOSE 3000

# Command to serve the build
CMD ["serve", "-s", "build", "-l", "3000"]
