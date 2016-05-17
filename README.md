# spring-boot-test-smtp

## Building

Requires
* Java 1.8
* Maven

```
mvn clean package
```

## Runing locally

```
mvn spring-boot:run
```

To view the applicaiton, open a browser and navigate to http://localhost:8080

Remember to URL Encode the parameter names. 

## Example URL:

```
http://localhost:8080/send?to=rick%40rick-ross.com&from=rick%40rick-ross.com&subject=test+mail&body=body+test&host=127.0.0.1
```
