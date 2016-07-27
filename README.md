Hiperium Project: Platform for the Internet of Things.
========================
Author: Andres Solorzano
Level: Advanced  
Technologies: CDI, RESTful, JPA, EJB, JTA, NoSQL
Summary: This project is the control microservice that manages device controls in a smart home.  
Target Product: Wildfly 10
Source: <https://bitbucket.org/aosolorzano/hiperium-control>  

What is it?
-----------

The Hiperium Control is a microservice of the hiperium Project for the Internet of Things. This control project manages many devices existing in a smart home.

System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Wildfly 10 or later.

All you need to build this project is Java 8.0 (Java SDK 1.8) or later, Maven 3.0 or later, and Docker 1.10 or later.


Docker Image
-------------------

This repository contains the instructions needed to create a docker image based on the Hiperium Device Service.


Dependencies
-------------------

Docker Engine


Deploying
-------------------

Execute the following commands to run the docker image in your host computer:

* docker pull hiperium/hiperium-device
* docker run -it -d hiperium/hiperium-device


Access the application 
---------------------

The application will service many Restful functionalities that are running at the following URL: <http://localhost:8080/hiperium-control/api/control/>. You need the client that are developed using IonicFramework that use AngularJS to access the services.

