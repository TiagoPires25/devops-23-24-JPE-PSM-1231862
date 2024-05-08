# Class Assignment 3 - Part 1

## Introduction:

This project serves as a progression from the preceding assignments, CA1 and CA2, and is bifurcated into two distinct
sections.
The initial segment is a comprehensive tutorial delineating the process of establishing a VirtualBox virtual machine and
executing the CA1 and CA2 projects therein.
The primary objective of this first part is to furnish a detailed guide that elucidates the procedure of setting up the
virtual machine and executing each project.
This includes an understanding of the dependencies, the requisite steps for their execution, and the complexities
associated with running the projects on a machine devoid of a graphical interface.

## Table of Contents

1. [Create a Virtual Machine]
2. [Cloning class repository into the virtual machine]
3. [Testing CA1]
4. [Testing CA2- Part 1]
5. [Testing CA2- Part 2]
6. [Conclusion]

## Create a Virtual Machine

1. Download and install VirtualBox from the official website.
2. Download the Ubuntu 22.04.3 iso image from the official Ubuntu website.
3. Launch VirtualBox and initiate the creation of a new virtual machine by clicking on the "New" button.
4. In the creation wizard, specify "Linux" as the operating system type and "Ubuntu (64-bit)" as the version.
5. Allocate XGB of RAM to the virtual machine during the setup process depending on your machine capacity.
6. Proceed to create a virtual hard disk with default settings and size.
7. Mount the downloaded Ubuntu iso image and initiate the installation process by clicking on "Start".
8. Upon completion of Ubuntu installation, install the VirtualBox Guest Additions to enable features such as shared
   folders.
9. Configure the network settings of the virtual machine in VirtualBox by selecting the "Network" tab, choosing "
   Host-only Adapter", and confirming with "OK".
10. Finalize the network configuration within Ubuntu by editing the /etc/netplan/00-installer-config.yaml file with the
    appropriate network settings.

```yaml
enp0s8:
  addresses:
    - 192.168.56.5;
```

13. Implement the modifications by executing the command below:

```bash
sudo netplan apply
```

14. To facilitate SSH access to the virtual machine, install the openssh-server package with the following commands:

```bash
sudo apt update
sudo apt install openssh-server
```

15. Activate password authentication by modifying the /etc/ssh/sshd_config file and altering the following line:
    line:

```bash
PasswordAuthentication yes
```

16. Reboot the SSH service by executing the command below:

```bash
sudo systemctl restart ssh
```

17. To access the virtual machine via the SSH protocol, execute the command below:

```bash
ssh username@ip_address
```

17. In this case, replace username with your username (in this case, tiago) and ip_address with the IP address of your
    virtual machine (in this case, 192.168.56.5). So the command becomes:

```bash
ssh tiago@192.168.56.5
```

During the class assignment I used windows command prompt to access the VM via ssh, but you can use the virtual machine
terminal if you prefer.

## Cloning class repository into the virtual machine

In the context of this project, cloning the repository into the virtual machine is the first step towards setting up the
development environment.
This will allow you to access the project files and start working on the assignments.
The repository contains wrapper scripts for both Maven and Gradle, which means you can run the projects without having
to install Maven or Gradle on the virtual machine.
However, this tutorial will also guide you through the process of installing Maven and Gradle on the virtual machine.

1. To clone the repository into the virtual machine, execute the following steps:

- Open Terminal: Access the terminal in Ubuntu by pressing Ctrl + Alt + T or searching for "Terminal" in the
  applications menu.
- Generate SSH Key Pair: Execute the command ssh-keygen -t rsa -b 4096 -C "your_email@example.com", replacing "
  your_email@example.com" with your actual email address.
- Choose Location and Passphrase: Accept the default location (~/.ssh/id_rsa) by pressing Enter or specify a different
  location. Optionally, set a passphrase for extra security.
- Confirm SSH Key Generation: The SSH key pair will be generated, with the public key saved in a .pub extension file (
  e.g., id_rsa.pub) and the private key saved in a file without an extension (e.g., id_rsa).
- View Public Key: View the public key using the command cat ~/.ssh/id_rsa.pub and copy the entire content for use on
  platforms like GitHub or GitLab.
- Add Public Key to SSH Agent: Optionally, add the private key to the SSH agent for secure storage and use with the
  command ssh-add ~/.ssh/id_rsa.
- Use the SSH Key: The generated SSH key pair can now be used for authentication with remote servers or services that
  support SSH authentication.
- Clone Repository: Execute the command git clone:

```bash
git clone https://github.com/TiagoPires25/devops-23-24-JPE-PSM-1231862.git
```

2. Install the following dependencies in your virtual machine:

```bash
sudo apt install openjdk-17-jdk-headless
sudo apt install git
sudo apt install maven
sudo apt install gradle
```

# Testing CA1

To guarantee that the CA1 project is operational, you must allow execution permission with the following command:

```bash
chmod +x mvnw 
```

1. I used the wrapper script to run the project, to do so, execute the following command:

```bash
./mvnw spring-boot:run
```

To fully check if everytjing is working as expected, access the following URL in your browser:

```bash
http://192.168.56.5:8080/
```

Note: The above url is the one in accordance to the IP address of the virtual machine I set up earlier.
If you are running the project on a different machine, you should replace the IP address accordingly.

# Testing CA2- Part 1

The ca2/part1 project requires both server and client components to be functional.
The client component needs a graphical interface, so it's recommended to run it on the host machine.
Running the client on a virtual machine could lead to a Headless Exception due to the lack of a graphical environment.
On the other hand, the server component is ideally run on a virtual machine, aligning with common practices for server
deployment and remote access.

1. To guarantee that the CA2- Part 1 project is operational, you must allow execution permission with the following
   command

```bash
chmod +x gradlew 
```

2. In the terminal of the virtual machine execute the following commands:

```bash
cd devops-23-24-JPE-PSM-1231862/ca2/part1
./gradlew runServer
```

3. Now in the terminal of the host machine execute the following command in order to provide a client and a server side:

```bash
./gradlew runClient --args="192.168.56.5 59001"
```

Note: The above command is in accordance to the IP address of the virtual machine I set up earlier.
The port number is 59001, which is the default port number for the server component- you are able to edit in the
gradle.build file.

# Testing CA2- Part 2

Part2 maintains the same structure as its predecessors. It's crucial to note that this project necessitates Java 17 for execution. 
Begin by ensuring that the files have the appropriate execution permissions. 
Then, use either gradle or ./gradlew to initiate the application and perform the tasks outlined in the README.md file within the ca2/part2 directory.Part2 maintains the same structure as its predecessors. 
It's crucial to note that this project necessitates Java 17 for execution. 
Begin by ensuring that the files have the appropriate execution permissions. 
Then, use either gradle or ./gradlew to initiate the application and perform the tasks outlined in the README.md file within the ca2/part2 directory.

# Conclusion
In conclusion, this document provides a comprehensive guide on setting up and running the Class Assignment 3 - Part 1 project. 
It begins with the creation of a VirtualBox virtual machine and the installation of Ubuntu, followed by the process of setting up SSH access and cloning the class repository into the virtual machine.
The document further provides detailed instructions on how to test the CA1 and CA2 projects, ensuring that they are operational. 
It emphasizes the need for execution permissions and the use of wrapper scripts for running the projects. 
It also provides specific instructions for running the server and client components of the CA2 project.
The specific requirements for each project are highlighted, such as the need for Java 17 for the Part2 of the CA2 project. 
Furthermore, the document provides insights into the structure of the project, the dependencies involved, and the steps required for their execution.
It also addresses the complexities associated with running the projects on a machine devoid of a graphical interface.
Finally, the document highlights the requirements for running Part2 of the project, including the need for Java 17 and the appropriate execution permissions.
It guides the user on how to initiate the application using either gradle or ./gradlew and perform the tasks outlined in the README.md file within the ca2/part2 directory.
Overall, this document serves as a valuable resource for any developer looking to understand and run the Class Assignment 3 - Part 1 project.
It provides a clear and explicit guide that can help developers navigate the setup and execution process with ease.