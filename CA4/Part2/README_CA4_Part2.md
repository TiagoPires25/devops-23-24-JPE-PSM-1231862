DevOps CA4- Part 2 Report
===================

## Table of Contents

1. [Introduction to the class assignment]
2. [Setting up the environment]
3. [Implementation of the Web Dockerfile]
4. [Implementation of the Database Dockerfile]
5. [Implementation of the docker-compose file]
6. [Tagging both images and testing the application]
7. [Using Kubernetes as an alternative to Docker]
8. [Conclusion]

# Introduction to the class assignment

This report presents a technical exploration of containerization for the Spring Basic Tutorial application (Gradle version) for the second part of the forth class assignment. 
The primary focus is on leveraging Docker and Docker Compose to establish a containerized environment for application execution.

This document adopts a pedagogical approach, meticulously outlining the steps necessary to replicate the functionalities implemented in the assignment.
Here are some of the main topics covered in this report:

Implementation of Core Requirements with Docker and Docker Compose: 
Detailing the utilization of Docker and Docker Compose to construct a containerized environment for the Spring application. 
It elaborates on the configuration and execution processes employed to achieve this objective.

Development of Separate Web and Database Containers: 
The report explores the creation of distinct containers for the web application (running Tomcat) and the database (utilizing H2 server). 
It explains the rationale behind this separation and the configuration specifics for each container.

Leveraging Volumes for Database Persistence: 
This section elucidates the implementation of volumes to ensure persistence of the database files.
It explains how volumes enable data to be preserved beyond container lifecycles.

Publication of Images to Docker Hub:
The report outlines the process of publishing the constructed container images (web and database) to Docker Hub, a public registry for container images.
This facilitates sharing and deployment of the containerized application.
By following the comprehensive instructions provided in this report, readers with a fundamental understanding of Docker can effectively replicate the functionalities of the assignment and gain practical experience with containerization principles.

## Setting up the environment

To begin the class assignment you must copy the react-and-spring-data-rest directory from the CA2/Part2 directory to the CA4/Part2 directory.
To do so, you may follow these commands:

```bash
$ cp -r CA2/Part2/react-and-spring-data-rest CA4/Part2
```

## Implementation of the Web Dockerfile
After you are located in the CA4/Part2 directory, you must create a Dockerfile that will be responsible for building a Docker image that runs the Spring application with a React frontend. 
Let's break down each line:

```Dockerfile
FROM openjdk:17-jdk-slim:
```
This line sets the base image for the container. It uses the openjdk:17-jdk-slim image, which provides a slim version of OpenJDK 17 for running Java applications.

```Dockerfile
COPY ./react-and-spring-data-rest-basic .:
```
This line copies the contents of the current directory (where the Dockerfile resides) on your host machine to the /app directory within the container. 
This assumes your application code is in a folder named react-and-spring-data-rest-basic.

```Dockerfile
WORKDIR /app:
```
This line sets the working directory again to /app, potentially because previous commands might have changed it.

```Dockerfile
RUN chmod +x gradlew:
```
This line executes a command within the container during the build process. 
Here, it uses chmod +x gradlew to make the gradlew script executable. 
This script is likely used to build your Gradle project.

```Dockerfile
CMD ["./gradlew", "build"]:
```

This line defines the default command to run when the container starts. 
It uses CMD with an array of arguments: ["./gradlew", "build"]. 
This instructs the container to execute the gradlew build command during startup, which likely builds your Spring application.

```Dockerfile
EXPOSE 8080:
```
This line informs Docker that the container listens on port 8080. 
This doesn't map the port to the host machine by default, but it allows you to do so when running the container. 
Your Spring application likely uses port 8080 for incoming requests.

```Dockerfile
RUN ls -la:
```
This line executes another command during the build process. 
Here, it uses ls -la to list the contents of the /app directory, which might be helpful for debugging purposes.
However, this command's output isn't captured or used within the image, so it can be removed for a cleaner Dockerfile.

```Dockerfile
CMD ["java", "-jar", "build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar"]:
```
Since the previous CMD instruction gets overwritten by this one, this line defines the final command to run when the container starts.
It uses CMD with an array of arguments: ["java", "-jar", "build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar"].
This instructs the container to run the built JAR file (build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.jar), assuming your Spring application is packaged into this file during the build process.

## Implementation of the Database Dockerfile

Now we will implement the Dockerfile for the database container.
I will break down each line:


```Dockerfile
FROM ubuntu:focal
```
Use Ubuntu 20.04 ("focal") as the base image for this container.
```Dockerfile
RUN apt-get update && \
  apt-get install -y wget openjdk-17-jdk-headless && \
   rm -rf /var/lib/apt/lists/*
```
 Update the package lists to get the latest version information.
Install wget (a tool to download files from the internet) and the headless version of OpenJDK 17 (a version of Java without GUI components).
Remove the package lists to reduce the image size.

```Dockerfile
WORKDIR /opt/h2

```
Set the working directory inside the container to /opt/h2

```Dockerfile
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O h2.jar
```
Download the H2 database JAR file and save it as h2.jar in the current working directory.

```Dockerfile
EXPOSE 8082
EXPOSE 9092

```
Declare that the container will use port 8082 (for the H2 web interface).
Declare that the container will use port 9092 (for the H2 TCP server).
```Dockerfile   
CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-ifNotExists", "-web", "-webAllowOthers", "-webPort", "8082", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092"]
```
Define the command to run when the container starts.


## Implementation of the docker-compose file

```Dockerfile
services:
  db:
    build:
      context: .
      dockerfile: Dockerfile_Database
    container_name: CA4_Part2_db
    ports:
      - "8082:8082"
      - "9092:9092"
```
This section defines the different services (containers) that make up your application.

```Dockerfile
    volumes:
      - h2-data:/opt/h2-data
```
Mounts a volume to the container

```Dockerfile
    networks:
      CA4_network:
        ipv4_address: 192.168.33.11
```
Connects the container to the specified network

```Dockerfile
  web:
    build:
      context: .
      dockerfile: Dockerfile_Web
    container_name: CA4_Part2_web
    ports:
      - "8080:8080"
    networks:
      CA4_network:
        ipv4_address: 192.168.33.10
    depends_on:
      - "db"
```

We define the web service, which builds the web container using the Dockerfile_Web file.

```Dockerfile
volumes:
  h2-data:
    driver: local
```

This section defines the volumes used by the containers.

```Dockerfile
networks:
  CA4_network:
    ipam:
      driver: default
      config:
        - subnet: 192.168.33.0/24
```
In the networks section, we define the network used by the containers.


## Tagging both images and testing the application

Firstly we will use the following command to build the images:

```bash
$ docker-compose build
```

After the images are built, we can run the containers using the following command:

```bash
$ docker-compose up
```

To test the application, you can open a web browser and navigate to http://localhost:8080 and http://localhost:8082.

Then we will tag both images using the following commands:

```bash
$ docker tag part2-web  tiagopires1862/part2-web
 docker push tiagopires1862/part2-web
 
 docker tag part2-db  tiagopires1862/part2-db
  docker push tiagopires1862/part2-db
```
## Using Kubernetes as an alternative to Docker
Kubernetes is an open-source platform designed to automate the deployment, scaling, and management of containerized applications.
It groups containers into logical units for easy management and discovery, providing a platform to manage containerized workloads and services.
Kubernetes was originally developed by Google and is now maintained by the Cloud Native Computing Foundation (CNCF).

The general steps to complete the class assignment using Kubernetes are as follows:

Web Deployment and Service:

```yaml
# web-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: web-deployment
  labels:
    app: web
spec:
  replicas: 1
  selector:
    matchLabels:
      app: web
  template:
    metadata:
      labels:
        app: web
    spec:
      containers:
      - name: web
        image: tiagopires1862/part2-web
        ports:
        - containerPort: 8080

---
# web-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: web-service
spec:
  selector:
    app: web
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080
  type: LoadBalancer
```

Database Deployment and Service:

```yaml
# db-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: db-deployment
  labels:
    app: db
spec:
  replicas: 1
  selector:
    matchLabels:
      app: db
  template:
    metadata:
      labels:
        app: db
    spec:
      containers:
      - name: db
        image: tiagopires1862/part2-db
        ports:
        - containerPort: 8082
        - containerPort: 9092
        volumeMounts:
        - name: h2-data
          mountPath: /opt/h2-data
      volumes:
      - name: h2-data
        persistentVolumeClaim:
          claimName: h2-data-pvc

---
# db-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: db-service
spec:
  selector:
    app: db
  ports:
    - protocol: TCP
      port: 8082
      targetPort: 8082
    - protocol: TCP
      port: 9092
      targetPort: 9092
  type: ClusterIP
```

Persistent Volume Claim:

```yaml
# h2-pvc.yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: h2-data-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
```

Apply the manifests and verify the deployment:
    

```bash
$ kubectl apply -f h2-pvc.yaml
$ kubectl apply -f db-deployment.yaml
$ kubectl apply -f db-service.yaml
$ kubectl apply -f web-deployment.yaml
$ kubectl apply -f web-service.yaml
$ kubectl get pods
$ kubectl get svc

```


## Conclusion

This assignment effectively demonstrated the practical implementation of containerization and orchestration using Docker, Docker Compose, and Kubernetes for a Spring application with a React frontend and an H2 database. 
By containerizing the application and separating the web and database components, we achieved modularity and scalability essential for modern microservices architecture.
By completing this assignment, we gained a comprehensive understanding of containerizing and orchestrating a full-stack application. 
These skills are critical for modern software development and deployment, enabling efficient management of complex application stacks. 
The principles and practices learned here are applicable to a wide range of projects, ensuring portability, scalability, and resilience.
This assignment lays a solid foundation for further exploration into advanced DevOps techniques and tools, preparing us for managing containerized applications in dynamic and demanding environments.