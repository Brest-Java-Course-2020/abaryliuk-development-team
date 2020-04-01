![Java CI](https://github.com/Brest-Java-Course-2020/abaryliuk-development-team/workflows/Java%20CI/badge.svg)
# abaryliuk-development-team

Development team demo application

## How build
Setup java 8 and Maven, see [enviroment_setup.md](enviroment_setup.md) 
  
      
## Build project 
Goto Project folder and execute  
    
    mvn clean install

## Rest server

### Start Rest using Maven Jetty plugin 
    
To start Rest using Maven Jetty plugin use:

```
cd development-team-rest-app
mvn jetty:run
```

## Available REST endpoints  

### Test controller

```
curl --location --request GET 'http://localhost:8080/testController'
```

