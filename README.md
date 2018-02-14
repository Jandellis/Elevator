Elevator Controller
===================
James McNamara

Running
-------
Running the Application via running the following from a terminal
* Windows - mvnw.cmd spring-boot:run
* Linux - ./mvnw spring-boot:run

The following endpoints are then available to be called
* POST - localhost:8080/elevator/{elevatorID}/floor/{floor}
  * Move the elevator to the given floor 
* POST - localhost:8080/elevator/{elevatorID}/up
  * Move the elevator up one floor
* POST - localhost:8080/elevator/{elevatorID}/down
  * Move the elevator down one floor
* POST - localhost:8080/elevator/{elevatorID}/stop
  * Stop the elevator

The admin interface is available at localhost:8080/admin this will show all the commands that have been entered and the current state of the elevators

Running the Tests via running the following from a terminal
* Windows - mvnw.cmd test
* Linux - ./mvnw test

Requirements
------------
* Java 8 JDK
* Internet connection to download libraries though Maven

Design
------
I created a number of Rest endpoints to be used by the hardware company to control the lift.
I used Spring Boot so that it would do the configuration of all the endpoints for me and I just had to create the endpoints.
The Thymeleaf template was used as a simple way to inject the elevator status into the html. 
I Used an embedded version of Tomcat to reduce the complexity required to set up the project.
