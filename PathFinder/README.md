# PathFinder

@Author: Deepanshu Bhatti

## Build and Deploy

### Intellij

* Using Intellij just go to PathFinderApplication.java, then go to Run and click on Run 

### Command Line

#### Build Application
```sh
# Change directory to Project Directory
cd D:\Projects\PathFinder

# build command
gradlew clean build
```

#### Deploy Application

```sh
# Deploy using
java -jar build/libs/PathFinder-1.0-SNAPSHOT.jar
```

## Access URL

```sh
http://localhost:8080/getShortestPath?name=Deepanshu
```
Result will be
```json
{
  "id": 3,
  "content": "Hello, Deepanshu!"
}
```

---
