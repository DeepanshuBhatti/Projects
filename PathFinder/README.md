# PathFinder

@Author: Deepanshu Bhatti

## Introduction

Path finder is a SpringBoot restful application which provides APIs to get the shortest distance and the shortest
 path from source to destination.

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
http://localhost:8080/PathFinderService/getAllCityInfo?format=json

# Result
[
    {
        "id": 1,
        "name": "Delhi",
        "abbrev": null
    },
    {
        "id": 2,
        "name": "Mumbai",
        "abbrev": null
    },
    {
        "id": 3,
        "name": "Agra",
        "abbrev": null
    },
    {
        "id": 4,
        "name": "Jaipur",
        "abbrev": null
    }
]
```

```sh
http://localhost:8080/PathFinderService/getShortestDistance?sourceName=delhi&destinationName=mumbai&format=json

# Result
1428
```
---
