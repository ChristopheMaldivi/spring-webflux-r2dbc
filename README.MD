### What is in the box?

Spring webflux implementation of a simple use case:
 - use the Country REST API (http://restcountries.eu/#api-endpoints-name) to retrieve info on a given country. For this we use the webflux `WebClient` client.
 - persist data asynchronously in Postgres thanks to the R2DBC asynchronous SQL database driver
 
  
Here we rely on the Reactor API, based on `Mono` and `Flux`. One could change it to rely on `CompletableFuture` api.

Now we should use *Webflux* for asynchronous implementations with Spring, and avoid the `@Async` stuff. A great video which explains that in details: [Servlet vs Reactive Stacks in Five Use Cases](https://www.infoq.com/presentations/servlet-reactive-stack/)

### Build, run, test

 - run database: `./rundb.sh`
 - run server: `./mvnw clean spring-boot:run`
 - retrieve info for China: `curl localhost:8080/countries/china`
 - retrieve data from database: `curl localhost:8080/countries/all`
  

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Webflux](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/web-reactive.html)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/html/spring-boot-features.html#boot-features-r2dbc)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)
* [R2DBC Homepage](https://r2dbc.io)
* [Mono API reference](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)


### License

MIT License
