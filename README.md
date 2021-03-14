#Cross-Platform
Cross platform is a file attribute reader. It is a service that receives a path or directory, and returns the attributes of each file found in the directory. 
#Build Tool
- #####Maven
#DevOps
- #####Docker 
- #####Git
#Frameworks
####Spring Boot 
- Service build framework.
- Service Endpoint Url (http://localhost:8080/cross-app/files)
####Spring Actuator 
- Used for service **monitoring**.
  - Url (http://localhost:8080/manage-cross-platform/*)
- ######Health check
  - Url (*/health)
- ######Information
  - Url (*/info)
- ######Metrics
  - Url (*/metrics)
####Spring Controller Advice 
- Used for exception handling strategy.
####Swagger Api Documentation 
- Used for Api documentation.
  - Url (http://localhost:8080/cross-swagger-ui.html)
####Lombok 
- Java Library used for reducing boiler plate code such as getters and setters etc.
####Logging
- Used log4j2 for generating log files.
  - Path (Cross-Platform/logs).
#Run Instructions
  - Please have Docker, Java Compatible IDE (e.g IntelliJ) installed on your machine.
  - JDK 1.8 or newer, but note that if you use newer java versions will as well need to change docker JDK in the dockerfile to avoid exceptions.
  - Clone service
    - Url (https://github.com/Sebolaishi/Cross-Platform.git)
  - Run maven refresh to import all dependencies.
  - Run maven [clean, install] goals to build service jar.
#Dockerization & Deployment
  - Please once again verify that your compile JDK and dockerfile JDK are the same versions to avoid exceptions.
- ######Build Image
  - Execute docker command - ( docker build -t cross-platform . )
- ######Run & port binding
  - Execute docker command - (docker run -p 8080:8080 cross-platform)
- ######Image name
  - cross-platform
#Future Improvements
- Service security layer implementation such that only authorized clients can consume the service.
  - ######Security Mechanism
    - JWT
    - Spring Security

