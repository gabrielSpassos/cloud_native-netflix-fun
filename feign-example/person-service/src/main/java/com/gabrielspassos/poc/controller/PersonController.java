package com.gabrielspassos.poc.controller;

import com.gabrielspassos.poc.exception.IdNotExistException;
import com.gabrielspassos.poc.model.PersonModel;
import com.gabrielspassos.poc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonModel getPersonById(@PathVariable("id") int id) throws IdNotExistException {
        return personService.getPersonById(id);
    }
}
