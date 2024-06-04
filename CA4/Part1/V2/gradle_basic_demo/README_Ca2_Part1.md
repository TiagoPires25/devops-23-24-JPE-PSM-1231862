# Class Assignment 2 - Part 1

## Introduction

# Class Assignment 2 - Part 1

## Introduction

1. This assignment gives you practical experience with Gradle, a tool for automating Java project builds. You'll learn to manage dependencies, run tasks, and automate builds.
2. The assignment is split into parts, each focusing on a different Gradle aspect. In Part 1, you'll start with a basic Gradle project and gradually add tasks and tests. You'll learn to add a server run task, a unit test, and tasks to backup and archive the source code.
3. By the end of this assignment, you'll have a better understanding of Gradle's functionality and its use in automating build and testing processes in Java projects. This knowledge will be useful for future complex Java projects.
## Table of Contents

1. [Getting Started](#Getting-started)
2. [Implementing Changes](#Implementing-Changes)
    - [Part 1: Adding the runServer task](#Part-1-Adding-the-runServer-task)
    - [Part 2: Adding the test class and unit test](#Part-2-Adding-the-test-class-and-unit-test)
    - [Part 3: Adding the Copy task](#Part-3-Addding-the-Copy-task)



## Getting Started

The first step consists on cloning the repository (https://bitbucket.org/pssmatos/gradle_basic_demo/src) and copying the application into a new folder. 


1. Go to gradle_basic_demo repository:
   ```bash
   cd path/to/gradle_basic_demo
   ```
- path to the repository folder using the cd command

2. Copy the application into a new CA2/Part1 folder:
   ```bash
   cp -r . ../CA2/Part1
   cd ../CA2/Part1
   ```
    - using the cp command to copy the contents of the repository to a new folder, and then navigating to the new folder

3. Initialize a new git repository in the new folder:
   ```bash
   git init
   ```
    - initializes a new git repository in the current directory
4. Add the files to the repository:
   ```bash
   git add .
   ```
    - adds the files in the local repository and stages them for commit
5. Commit the added files:
   ```bash
   git commit -m "Added part1 of CA2 example application"
   ```
    - records the changes to the repository
6. Push to the remote repository:
   ```bash
   git push
   ```
    - pushes the changes in the local repository to the remote repository


### Adding functionality to the application
#### Part 1: Adding a new task to execute the server

The goal is to create a new task that will execute the server. There are some steps to follow in order to achieve this goal:
1.   In the build.gradle file, add the following task:
```gradle

task runServer(type:JavaExec, dependsOn: classes){
    group = "DevOps"
    description = "Launches a chat server that listens on port 59001"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}

    ```

-This specific task will execute the ChatServerApp class, which is the main class of the application. The task will listen on port 59001.

#### Part 2: Adding the unit test

The goal is to create a new test class and a unit test for the application. The steps to do so are:
1. Create the test class and unit test:
   ```bash
   mkdir -p src/test/java/basic_demo
   touch src/test/java/basic_demo/ChatServerAppTest.java
   ```
   - creates the test class in the test folder

2. Setup the test class:
```java
package basic_demo;


import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
}
```
3. Add a unit test to the test class:
```java
   @Test public void testAppHasAGreeting() {
      App classUnderTest = new App();
      assertNotNull("app should have a greeting", classUnderTest.getGreeting());
   }
```
4. Add the JUnit dependency to the build.gradle file:
```gradle
dependencies {
    // Use Apache Log4J for logging
    implementation group: 'org.apache.logging.log4j', name: 'log4j-api', version: '2.11.2'
    implementation group: 'org.apache.logging.log4j', name: 'log4j-core', version: '2.11.2'
    testImplementation 'junit:junit:4.12'
}
```

5. Compile the project using the terminal:
```bash
./gradlew build
```
6. Add and commit features to the remote repository:
```bash
git commit -a -m "Add testing to the application"
```
7. Push the changes to the remote repository:
```bash
git push
```

#### Part 3: Adding the Copy task
The main purpose of this task is to copy the source files of the application to a target directory. There are some steps to follow in order to achieve this goal:
1. Add the new task to the build.gradle file:
```gradle
task backupSources(type: Copy) {
    from 'src'
    into 'backup'
}
```
2. Compile the project using the terminal:
```bash
./gradlew build
```

3. Add and commit features to the remote repository:
```bash
git commit -a -m "Add copy task"
```
4. Push the changes to the remote repository:
```bash
git push
```

#### Part 4: Adding the Zip task
The main purpose of this task is to zip the source files of the application to a target directory. There are some steps to follow in order to achieve this goal:
1. Add the new task to the build.gradle file:
```gradle
task zip(type: Zip) {
    from 'src'
    archiveFileName = 'src.zip'
}
```
2. Compile the project using the terminal:
```bash
./gradlew build
```
3. Add and commit features to the remote repository:
```bash
git commit -a -m "Add zip task"
```
4. Push the changes to the remote repository:
```bash
git push
```
5. Tag the repository to mark the completion of Part 1:
```bash
git tag ca2-part1
git push --tags
```
