DevOps CA4 - Part 1 Report
===================

## Table of Contents

1. [Introduction to the class assignment]
2. [Setting up the environment]
3. [Implementation of the first version of the Dockerfile]
4. [Implementing the second version of the Dockerfile]
5. [Testing the implementation of the class assignment]
6. [Conclusion]

# Introduction to the class assignment

This assignment is focused on the practical application of Docker, a platform that uses OS-level virtualization to deliver software in packages called containers. 
The main objective is to create Docker images and run containers using the chat application from CA2, which can be found here.  
The goal is to package and execute the chat server in a Docker container. 
This involves creating a Docker image using a Dockerfile, tagging the image, and publishing it on Docker Hub. 
The chat client should be executable on the host computer and be able to connect to the chat server running in the Docker container.  
This assignment is divided into two parts to explore the concept of Docker images:  
In the first part, the chat server is built "inside" the Dockerfile.
In the second part, the chat server is built on the host computer and the resulting JAR file is copied "into" the Dockerfile.
Please note that the JDK used in the Docker container should be the same or newer than the one used to build the application.
The following sections of this README provide a detailed tutorial on how to implement these requirements, including the steps taken and justifications for certain decisions. 
By following this tutorial, you should be able to reproduce the assignment.

## Setting up the environment

*The first step is to create a new directory named CA4, a subdirectory for the CA4 assignment and another one for the first and second versions of the Dockerfile.
*The following commands can be used to create the directories:

```bash
$ mkdir -p CA4/
$ mkdir -p CA4/Part1
$ mkdir -p CA4/Part2
$ mkdir -p CA4/Part2/V1
$ mkdir -p CA4/Part2/V2
```
*Copy the chat server project from CA2 to the CA4 directory. This project will be used to create the Docker image.
*The following commands can be used to copy the project:
```bash
$ cp -r CA2/Part1/gradle_basic_demo CA4/Part1/V2
```


*You must install Docker on your computer to proceed with the assignment. 
Docker is a platform that uses OS-level virtualization to deliver software in packages called containers.
You must also crate a free Docker account, this will facilitate the process of pushing the Docker image to Docker Hub.

## Implementation of the first version of the Dockerfile
After you are located in the CA4/Part1 directory, you must create a Dockerfile to build the Docker image.
```bash
$ cd CA4/Part1
$ touch Dockerfile_Web
```

Now lets go through each line of the Dockerfile:

```Dockerfile
FROM gradle:jdk17 as builder:
```
This line is specifying the base image for the first stage of the build. It's using the gradle:jdk17 image, which includes Gradle and JDK 17. The as builder part is naming this stage of the build "builder".

```Dockerfile
LABEL author="Tiago Pires": 
```
This line is adding metadata to the image. It's setting the "author" label to "Tiago Pires"- you should change this to your own name.

```Dockerfile
WORKDIR /ca4-part1:
```
This line is setting the working directory in the Docker container to /ca4-part1. All subsequent commands (RUN, CMD, ENTRYPOINT, COPY, and ADD) will be run from this directory.

```Dockerfile
RUN git clone https://bitbucket.org/pssmatos/gradle_basic_demo.git: 
```
This line is running the git clone command to clone the gradle_basic_demo repository from Bitbucket into the current directory (/ca4-part1).

```Dockerfile
WORKDIR /ca4-part1/gradle_basic_demo:
```

This line is changing the working directory to /ca4-part1/gradle_basic_demo, where the chat server project is located.

```Dockerfile
RUN chmod +x gradlew:
```
This line is making the gradlew script executable. The gradlew script is used to build the chat server project with Gradle.

```Dockerfile
RUN ./gradlew build:
```
This line is running the ./gradlew build command to build the chat server project with Gradle. The resulting JAR file will be located in the build/libs directory.

```Dockerfile
FROM openjdk:17-jdk-slim:
```
This line is specifying the base image for the second stage of the build. It's using the openjdk:17-jdk-slim image, which includes OpenJDK 17.

```Dockerfile
COPY --from=builder /ca4-part1/gradle_basic_demo/build/libs/*.jar ca4-part1.jar:
```
This line is copying the JAR file(s) built in the "builder" stage from the /ca4-part1/gradle_basic_demo/build/libs/ directory to the /ca4-part1 directory in the new stage of the build. It's renaming the copied JAR file(s) to ca4-part1.jar.

```Dockerfile
ENTRYPOINT ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]:
```
This line is setting the entry point for the Docker container. 
When the Docker container is run, it will execute the command java -cp ca4-part1.jar basic_demo.ChatServerApp 59001.
This command runs the basic_demo.ChatServerApp class from the ca4-part1.jar file, passing 59001 as an argument.

Now that the Dockerfile is complete, you can build the Docker image using the following command:

```bash
$ docker build -t ca4-part1:v1 .
```
After a successful build, you can run the Docker container using the following command:

```bash
$ docker run -p 59001:59001 ca4-part1:v1
```

Executing the bellow command will run the chat client on the host computer and connect to the chat server running in the Docker container:

```bash
./gradlew runClient
```

Now we can tag the image with your Docker Hub username and push it to Docker Hub.
First, log in to Docker Hub using the following command (given that you already created a Docker Hub account and are logged in):

```bash
$ docker login
```

Now, tag the image with your Docker Hub username and push it to Docker Hub using the following commands:
Do not forget to replace your-docker-hub-username with your actual Docker Hub username.

```bash
$ docker tag ca4-part1:v1 your-docker-hub-username/ca4-part1:v1
$ docker push your-docker-hub-username/ca4-part1:v1
```



## Implementing the second version of the Dockerfile

Once again we must create a Dockerfile, but this time in the CA4/Part2/V1 directory.
```bash
$ cd CA4/Part2/V2
$ touch Dockerfile_Web
```

Now lets go through each line of the Dockerfile for the second version:


```Dockerfile
FROM openjdk:17-jdk-slim as builder:
```
This line is specifying the base image for the first stage of the build. It's using the openjdk:17-jdk-slim image, which includes OpenJDK 17. The as builder part is naming this stage of the build "builder".

```Dockerfile
WORKDIR /app: 
```
This line is setting the working directory in the Docker container to /app. All subsequent commands (RUN, CMD, ENTRYPOINT, COPY, and ADD) will be run from this directory.

```Dockerfile
COPY ./gradle_basic_demo:
```
This line is copying the chat server project from the host computer to the /app directory in the Docker container.

```Dockerfile
RUN chmod +x /app/gradlew:
```
This line is making the gradlew script executable. The gradlew script is used to build the chat server project with Gradle.

```Dockerfile
FROM openjdk:17-jdk-slim:
```
This line is specifying the base image for the second stage of the build. It's using the openjdk:17-jdk-slim image, which includes OpenJDK 17.

```Dockerfile   
WORKDIR /app:
```
This line is setting the working directory in the Docker container to /app. All subsequent commands (RUN, CMD, ENTRYPOINT, COPY, and ADD) will be run from this directory.

```Dockerfile
COPY --from=builder /app/build/libs/basic_demo-0.1.0.jar /app/basic_demo-0.1.0.jar:
```
This line is copying the JAR file built in the "builder" stage from the /app/build/libs/ directory to the /app directory in the new stage of the build.
    
```Dockerfile
EXPOSE 59001: 
    
```
This line is telling Docker that the container listens on the specified network port at runtime. Here, it's exposing port 59001.

```Dockerfile
ENTRYPOINT ["java", "-cp", "/app/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]: 
```
This line is setting the entry point for the Docker container. 
When the Docker container is run, it will execute the command java -cp /app/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001.
This command runs the basic_demo.ChatServerApp class from the basic_demo-0.1.0.jar file, passing 59001 as an argument.

Now that the Dockerfile is complete, you can build the Docker image using the following command:

```bash
$ docker build -t ca4-part1:v2 .
```
After a successful build, you can run the Docker container using the following command:

```bash
$ docker run -p 59001:59001 ca4-part1:v2
```

Executing the bellow command will run the chat client on the host computer and connect to the chat server running in the Docker container:

```bash
./gradlew runClient
```

Now we can tag the image with your Docker Hub username and push it to Docker Hub.
First, log in to Docker Hub using the following command (given that you already created a Docker Hub account and are logged in):

```bash
$ docker login
```

Now, tag the image with your Docker Hub username and push it to Docker Hub using the following commands:
Do not forget to replace your-docker-hub-username with your actual Docker Hub username.

```bash
$ docker tag ca4-part1:v1 your-docker-hub-username/ca4-part1:v1
$ docker push your-docker-hub-username/ca4-part1:v1
```



## Conclusion

In this assignment, we have explored the practical application of Docker, a powerful platform that uses OS-level virtualization to deliver software in packages known as containers.
We have successfully created Docker images and run containers using the chat application from CA2.

The assignment was divided into two parts to explore different aspects of Docker images.
In the first part, we built the chat server "inside" the Dockerfile, while in the second part, we built the chat server on the host computer and copied the resulting JAR file "into" the Dockerfile.
This approach allowed us to understand the flexibility and versatility of Docker in managing and deploying applications.

We have also learned the importance of using the correct JDK version in the Docker container, which should be the same or newer than the one used to build the application.
This is crucial to avoid runtime errors related to unsupported class file versions.
Furthermore, we have learned how to tag Docker images and publish them on Docker Hub, making them accessible to others.
This is an important step in sharing and distributing our applications.
By following the detailed tutorial provided in this README, we were able to reproduce the assignment, demonstrating the reproducibility of Docker-based deployments.

In conclusion, Docker is a powerful tool for creating isolated and reproducible environments, making it easier to deploy and distribute applications.
The knowledge and skills gained from this assignment are valuable for any developer working in a modern software development environment.