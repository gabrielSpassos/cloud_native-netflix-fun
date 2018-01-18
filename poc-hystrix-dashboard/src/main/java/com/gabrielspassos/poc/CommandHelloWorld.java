package com.gabrielspassos.poc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandHelloWorld extends HystrixCommand<Message> {

    private final String name;
    private long seconds;

    private final static String COMMAND_NAME = "ExampleHelloWorld";
    private final static int TIMEOUT = 3000;

    public CommandHelloWorld(String name, long seconds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(COMMAND_NAME))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(TIMEOUT)));
        this.name = name;
        this.seconds = seconds;
    }

    @Override
    protected Message run() throws InterruptedException {
        Message message = new Message();
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){

        }
        message.setText("Hello "+name+"! Thread waited for "+seconds+" seconds");
        return message;

    }

    @Override
    protected Message getFallback(){
        Message message = new Message();
        message.setText("Hello World! Thread waited for 5 seconds. This is a fallback message");
        return message;

    }
}

