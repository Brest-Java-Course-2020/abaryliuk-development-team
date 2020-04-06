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

##Projects

###findAll

```
curl --location --request GET 'http://localhost:8080/projects'
```

#### findById

```
curl --request GET 'http://localhost:8080/projects/1' | json_pp
```
### create

```
curl --location --request POST 'http://localhost:8080/projects' \
--header 'Content-Type: application/json' \
--data-raw '{"description":"Test","dateAdded":"2019-07-15"}'
```

### create by description

```
curl --location --request POST 'http://localhost:8080/projects/addByDescription' \
--header 'Content-Type: application/json' \
--data-raw '{"description":"Test"}'
```

### update

```
curl --location --request PUT 'http://localhost:8080/projects' \
--header 'Content-Type: application/json' \
--data-raw '{"projectId":2,"description":"Test update","dateAdded":"2019-08-13"}'
```

### delete

```
curl --location --request DELETE 'http://localhost:8080/projects/delete/1'
```

##ProjectsDtoController

### findBetweenDates

```
curl --location --request GET 'http://localhost:8080/projectsDto/?dateStart=2020-01-01&dateEnd=2020-03-01'
```

###findAll

```
curl --location --request GET 'http://localhost:8080/projectsDto/findAll'
```

##DevelopersController

###findAll

```
curl --location --request GET 'http://localhost:8080/developers'
```

### findById

```
curl --location --request GET 'http://localhost:8080/developers/1'
```

###create

```
curl --location --request POST 'http://localhost:8080/developers' \
--header 'Content-Type: application/json' \
--data-raw '{"firstName":"Ivan","lastName":"Ivanov"}'
```

###update

```
curl --location --request PUT 'http://localhost:8080/developers' \
--header 'Content-Type: application/json' \
--data-raw '{"developerId":1,"firstName":"Test","lastName":"Test"}'
```

###delete

```
curl --location --request DELETE 'http://localhost:8080/developers/1'
```