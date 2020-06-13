# Path Finder React Install

## Installation Process

- Install Node Js from <https://nodejs.org/en/download/>
- Check if npm and node are present in path

```sh
where npm
# C:\Program Files\nodejs\npm
# C:\Program Files\nodejs\npm.cmd
# C:\ProgramData\Anaconda3\npm
# C:\ProgramData\Anaconda3\npm.cmd
# C:\Users\DeepanshuBhatti\AppData\Roaming\npm\npm
# C:\Users\DeepanshuBhatti\AppData\Roaming\npm\npm.cmd

where node
# C:\Program Files\nodejs\node.exe
# C:\ProgramData\Anaconda3\node.exe

npm -v
# 6.14.5

node -v
# v10.16.3

# To Update node and npm to latest version
npm install -g npm@latest
```

### Install latest create-react-app

```sh
# Remove older version of create-react-app
npm rm -g create-react-app

# Install latest version of  create-react-app
npm install -g create-react-app
```

### Create pathfinder-react

```sh
# Create React App with Typescript
npx create-react-app pathfinder-react --template typescript
```

---
