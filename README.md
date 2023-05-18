# SpringEmailServiceDemo
This project is only DEMO, where you can check for implement mail service in spring boot application.
Also, here you can see a simple implements of blocking and activating accounts by email.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


## Prerequisites:
- JDK 17 or later


## Installing:
1) Clone repository:
    `git clone https://github.com/Maslyna/SpringEmailService.git`
2) Build Maven project:
    - To build the application execute the following commands in the project folder (where pom.xml and mvnw are located):

    - `./mvnw clean package` # this will build the project

    - For the first time it will download and install Maven version configured in the project settings (v.3.8.6) Next time the cached version will be used without redownloading.
    - After the build is completed, the folder ```/target``` will be created with a compiled ```.jar``` ready to be launched.


## Running the Application:
Now you can launch the server for example (default port is 8080):
- `java -jar ./target/*.jar`
- To see more information follow link: [CONTRIBUTING.md](https://github.com/Maslyna/SpringEmailService/blob/master/CONTRIBUTING.md)

###### Author:
- [@Maslyna](https://github.com/Maslyna)
