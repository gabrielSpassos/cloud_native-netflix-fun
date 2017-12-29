# Hystrix Dashborard 
## Spring Boot
### Hello World Poc

#### Usage

```
[method] GET
Endpoint: http://localhost:9000/hello/[name to say hello]/[seconds of the thread]
Result: Return a JSON with a text that contains the name and the seconds of the thread
OBS: Timeout of thread is 3 seconds

[method] GET
Endpoint: http://localhost:9000/helloSlow/[name to say hello]/[seconds of the thread]
Result: Return a JSON with a text that contains the name and the seconds of the thread
OBS: Timeout of thread is 25 seconds
```

#### References
* [Hystrix-DashBoard-GitHub](https://github.com/Netflix/Hystrix)
