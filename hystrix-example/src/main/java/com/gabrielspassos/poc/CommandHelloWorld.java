package com.gabrielspassos.poc;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;
    private long seconds;

    public CommandHelloWorld(String name, long seconds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(5000)));
        this.name = name;
        this.seconds = seconds;
    }

    @Override
    protected String run() throws InterruptedException {
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){

        }
        return "Hello " + name + "!" + "\nThread waited for " + seconds + " seconds";
    }

    @Override
    protected String getFallback(){
        return "\n***************************\nHello World! \nThread waited for "+seconds+
                " seconds"+"\nThis is a fallback message\n***************************\n";
    }






}

