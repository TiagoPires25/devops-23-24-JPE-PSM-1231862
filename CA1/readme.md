# Class Assignment 1 (CA1) Technical Report. DevOps.

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)

## Introduction
This technical report documents the development and findings of Class Assignment 1 (CA1), which focuses on implementing version control best practices using Git. The assignment is divided into two parts: initially working directly on the master branch and then introducing branching for feature development and bug fixes. Additionally, an alternative version control system is explored for comparison purposes.

## Prerequisites
- Git Bash
- Git repository

## Getting Started
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Follow the implementation steps provided in the report.

## Usage
After setting up the project, you can use Git commands to implement version control best practices as described in the report.




## Part 1: Directly Working on the Master Branch

### Objective

The objective of this section is to implement the initial setup and further requirements directly on the master branch without using other branches.

### Steps description

#### Setup and Initial Commit

1. Access the project directory:
   ```bash
   cd ~/OneDrive/Ambiente de Trabalho/DevOps/devops-22-23-JPE-PSM-1231862
    ```
    - this command changes the current directory to the specified path.

2. Copy the application to a newly created folder CA1:
   ```bash
   mkdir ./CA1
   cp -r . ../CA1
   cd ../
   ```
    - together, these commands create the CA1 folder, copy the contents of the current directory into the new folder. Then, it changes the current directory to CA1, allowing further operations within that directory.
    
3. Initializing the Git repository:
   ```bash
   git init
   ```
    - this command initializes a new Git repository in the current directory by adding a .git folder.
4. Add all files to the staging area:
    ```bash
   git add .
    ```
    - this command adds all files in the current directory to the staging area, preparing them for the next commit.
5. Commit the added files:
    ```bash
   git commit -m "First commit"
    ```
    - this command creates a new commit with the message "First commit", representing the initial state of the project.
6. Push the commit to the remote repository:
    ```bash
   git remote add origin https://github.com/TiagoPires25/devops-23-24-JPE-PSM-1231862.git
   git push -u origin master
    ```
   - The first command adds a remote repository to the local repository, named "origin" and located at the specified URL.
   - The second command pushes the local master branch to the remote repository, setting the upstream branch to "master".

#### Version Tagging

1. Tag the initial version of the application as `v1.1.0` and push the tag to the remote repository:
    ```bash
   git tag v1.1.0 -m "My first version"
   git push origin v1.1.0
    ```
    - this command creates a new tag named "v1.1.0" with the message "My first version".
       - the second command pushes the tag to the remote repository. marking the state of the project at that point.
#### New Feature - Job Years and Support

1. Creating Issues

   - Created issues related to the requirements for the first part of the assignment. 
   - The issues were created in the remote repository and are used to track the progress of the development. Then I will use fixes and closes to close the issues once the development is completed.

2. Implement the new feature directly on the master branch:
    ```bash
   git add .
   git commit -m "Added new jobYears field + data validation for the arguments Closes #1"
    ```
    - this commit message will close the following issue: "CA1: Add support to the jobYears field"

2. Push the commit to the remote repository:
    ```bash
   git tag v1.2.0 -m "Added tests for the Employees constructor"
   git push origin v1.2.0
   git tag ca1-part1
   git push origin ca1-part1
    ```
    - the first command creates a new tag named "v1.2.0" with the message "Added tests for the Employees constructor".

## Part 2: Using Branches for Development

### Objective

The objective of this section is to implement the new features and bug fixes using branches for development, following best practices for version control.

### Steps description

#### New Feature - Email Field

1. Creating Issues
    - Created issues related to the requirements for the second part of the assignment.

2. Create a new branch for the email field feature and switch to it:
   ```bash
   git checkout -b email-field
    ```
    - created and switched to the email-field branch. 
    - the "-b" flag creates a new branch and switches to it.

3. Used the branch to implement the features related to the e-mail attribute of the Employee constructor
   ```bash
   git add .
   git commit -m "Added email field, validation and tests closes #4 and #5"
   git push origin email-field
    ```
    - pushed the new feature into the e-mail field. 
    - 
3. Merge the email-field branch into the master and add v1.3.0 tag to mark the completion of the feature:
    ```bash
   git checkout master
   git merge --no-ff email-field
   git tag v1.3.0 -m "Merge branch 'email-field'"
   git push origin master
   git push origin v1.3.0
    ```
    - merged the 'email-field' into master.
    - created and pushed the v1.3.0 tag to mark the completion of the feature.
    - used the "--no-ff" flag to prevent fast-forwarding the merge, preserving the branch history.

#### Bug Fix - Invalid Email (without @ symbol)

1. Create several issues regarding this part of the assignment.
2. Create a new branch for the bug fix and switch to it:
    ```bash
   git checkout -b fix-invalid-email
    ```
    - created and switched to the fix-invalid-email branch.

2. Used the branch to implement the bug fix related to the e-mail attribute of the Employee constructor:
   ```bash
   git add .
   git commit -m "Added further e-mail validation and tests Closes #10"
   git push origin fix-invalid-email
   ```
    - pushed the new feature into the fix-invalid-email branch.
3. Merge the fix-invalid-email branch into the master and add v1.3.1 tag to mark the completion of the feature:
    ```bash
   git checkout master
   git merge fix-invalid-email
   git tag v1.3.1 -m "Merge branch 'fix-invalid-email'"
   git push origin master
   git push origin v1.3.1
   ```

4. Merge the fix-invalid-email branch into the master and add v1.3.1 tag to mark the completion of the feature:
    ```bash
   git tag ca1-part2 -m "Merge branch 'fix-invalid-email'"
   git push origin ca1-part2
    ```

## Part 3: Alternative Version Control System

### Importance of choosing the right Version Control System for your project
The Version Control System is a pivotal choice for the most efficient and correct development of the project.
Among other areas you may use the following ones for comparison: VCS support, Collaborator access, Interface and usability, Extensions and third-party integrations, Pricing plans.

### Mercurial as an Alternative

Mercurial and Git are both popular distributed version control systems with many similarities. 
They each have their strengths and weaknesses, making them suitable for different scenarios. 
Mercurial is a free and open-source distributed source control management tool, can manage projects of any size and offers a user-friendly interface. 
A key difference between Git and Mercurial is their handling of the staging area. Git uses a separate space for changes before they're committed to the repository, while Mercurial integrates the staging area into the working directory, potentially complicating change management and increasing error chances. 
In conclusion, Mercurial simplicity may appeal to beginners, while Git's advanced power and flexibility make it the preferred choice for most developers.

### Comparison to Git in bullet points

1. **Usability**: Git is more complex, and it requires your team to know it inside and out before using it safely and effectively. With Git, one sloppy developer can cause major damage for the whole team. Its documentation is also harder to understand.

2. **Security**: Security in Git vs. Mercurial depends on your level of technical expertise. Mercurial may be safer for less experienced users, but Git can offer ways to enhance safety (once you know what you are doing).

3. **Branches**: One of the main reasons developers use Git it's because of effective branching model. In Git, branches are only references to a certain commit. This makes them lightweight yet powerful.

### Summary/conclusions
- Fundamentally, Git and Mercurial are similar, and each has its merits as a VCS. They were just designed differently and require different levels of expertise.
- Git has become an industry standard, which means more developers are familiar it. Mercurial big advantage is that it’s easy to learn and use, which is useful for less-technical content contributors.

### Implementing the Assignment Goals with Mercurial: display of the most used commands
#### Setting up a repository in Mercurial
1. Install Mercurial on the local machine:
```bash
2. Clone the repository to your local machine:
```bash
cd path/to/directory
```
3. Initialize the repository:
```bash
hg init
```
4. Add all files to the repository:
```bash
hg add .
```
5. Commit the added files:
```bash
hg commit -m "Initial commit"
```
6. Push the commit to the remote repository:
```bash
hg push <repository-URL>
```
7. Tag the initial version of the application:
```bash
hg tag -m "First version of the application" v1.0
```

#### Implementing the New Feature - Email Field
```bash
hg branch email-field
```
To switch to the email-field branch, the following command can be used:
```bash
hg update email-field
```
To implement the new feature, the following commands can be used:
```bash
hg merge email-field
```
### Final Notes
- Used these websites as references for the comparing of the two version control systems:
  https://www.javatpoint.com/git-vs-mercurial    
  https://www.perforce.com/blog/vcs/mercurial-vs-git-how-are-they-different
- Despite initial confusion with command usage, all issues related to the assignment were successfully resolved, demonstrating the adaptability and problem-solving capabilities inherent in using version control systems like GitHub.