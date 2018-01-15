package com.gabrielspassos.poc;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private int number = 1;

    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET)
    public void listarCidades(){
        System.out.println("Testing ribbon: "+number);
        number++;
    }
}
