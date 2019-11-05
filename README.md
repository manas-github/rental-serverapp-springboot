# Rental application server ( backend service)
A complete back end server application developed to provide service for my other project ([rental app](https://github.com/manas-github/rental-app)). This application has been developed using Spring boot and hiberate and deployed on heroku. You can access the list of APIs developed from [swagger documentation](https://manas-rental-serverapp.herokuapp.com/swagger-ui.html).

The APIs developed are :
  Signup Controller
  
  Login Controller
  
  User Controller
  
  Product Controller
  
  Trending Product Controller
  
  Cart Controller
  
  Search History Controller
  
  
The application also have the JWT based security. So, all the requests (other than signup and login) need to be sent along with authorisation token in request header to get a valid response. Follow the instruction given in later part of this document on generating and appending header to request. 
  
# Getting started
Although the project is already hosted and avaliable [here](https://manas-rental-serverapp.herokuapp.com/swagger-ui.html). You can follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
To get started with this project you will need :

```JDK 1.8 or later```

```Maven 3.2 or later```

``` MySQL or any database```

```Spring tool suite or IntelliJ IDEA to directly import this project``` 

## Starting project

Download the project folder or clone from github

``` git clone https://github.com/manas-github/rental-serverapp-springboot.git```

Import the project in Spring Tool Suite or IntelliJ IDEA. Follow the instruction [here](https://blog.jetbrains.com/idea/2008/03/opening-maven-projects-is-easy-as-pie/), if you are not familiar with importing maven projects.

Once the importing is completed, change the database url, username and password in application.properties. 

After changing the details, build the project. (It may takes few minutes to build for the first time as it needs to download all the dependencies).

Once the build is completed, run the project as a Spring Boot Application.

The project will be started at your [localhost](http://localhost:8184/swagger-ui.html).

If you get a error that port is not available, then change the port in /resources/application.properties and open the corresponding localhost in your browser.

## Making API calls
All the api details with required parameter is document with the help of swagger and we can make the requests directly from swagger.

But, we will use postman for making requests here. If you don't have postman, download it from [here](https://www.getpostman.com/).

To use this application we need a user account. So, we will create a signup request from postman with appropriate body:

It returns true with status 201, which means that account has been created succesfully.

Now, we can login using the signup details.

It will return a encrypted token which we used for accessing all the apis.

Now, let's try to get list of all products using this token.

Add the header in postman request header tab with the token received from login request : 

  Key - Authorization & Value - Token $token 
  
Now, make the get request :

Here, we will get all the products which are available in database. If there is no products, we can add product using the respective api as available in documentation.

# License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/manas-github/rental-serverapp-springboot/blob/master/LICENSE) file for details


  






  

