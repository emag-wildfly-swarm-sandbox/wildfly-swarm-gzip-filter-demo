# wildfly-swarm-gzip-filter-demo

## Run the app
 
``` sh
$ ./mvnw clean wildfly-swarm:run
```

## Access a static file without gzip

``` sh
$ curl http://localhost:8080/assets/styles.css -v > /dev/null 
[...]
< Content-Length: 539
[...]
```

## Access a static file with gzip

``` sh
$ curl -H "Accept-Encoding: gzip" http://localhost:8080/assets/styles.css -v > /dev/null 
[...]
< Content-Encoding: gzip
< Content-Length: 309
[...]
```