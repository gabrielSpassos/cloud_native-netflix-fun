package com.gabrielspassos.poc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value="/hello/{name}/{seconds}",
            method= RequestMethod.GET)
    public Message hello(@PathVariable("name") String name ,@PathVariable("seconds") Long seconds){
        return new CommandHelloWorld(name,seconds).execute();
    }

}
