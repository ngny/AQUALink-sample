Build
=====

```
mvn package
```


Running
=======


To start the server:
```
java -jar target/simulator-1.0-1-jar-with-dependencies.jar server
```



To test a client homing request:
```
java -jar target/simulator-1.0-1-jar-with-dependencies.jar client homing
```


To test a client get-tests request:
```
java -jar target/simulator-1.0-1-jar-with-dependencies.jar client get-tests
```


To test a client send-results request:
```
java -jar target/simulator-1.0-1-jar-with-dependencies.jar client send-results
```


To test a client bulk request:
```
java -jar target/simulator-1.0-1-jar-with-dependencies.jar client bulk
```
