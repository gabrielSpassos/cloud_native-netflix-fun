package com.gabrielspassos.poc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandHelloWorld extends HystrixCommand<Message> {

    private final String name;
    private long seconds;
    Message message = new Message();

    public CommandHelloWorld(String name, long seconds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleHelloWorld"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(3000)));
        this.name = name;
        this.seconds = seconds;
    }

    @Override
    protected Message run() throws InterruptedException {
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){

        }

        message.setText("Hello "+name+"! Thread waited for "+seconds+" seconds");
        return message;
    }

    @Override
    protected Message getFallback(){
        message.setText("Hello World! Thread waited for 5 seconds. This is a fallback message");
        return message;
    }







}

