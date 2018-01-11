package com.gabrielspassos.poc;

import com.gabrielspassos.poc.exception.IdNotExistException;

import com.gabrielspassos.poc.model.PersonModel;
import com.gabrielspassos.poc.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PersonServiceTest {

    PersonService personService = new PersonService();

    @Test
    public void mustReturnPerson() throws IdNotExistException {
        PersonModel personModelReturned = personService.getPersonById(1);
        assertEquals(1,personModelReturned.getId());
        assertEquals("Gabriel",personModelReturned.getName());
        assertEquals("Passos",personModelReturned.getLastName());
        assertEquals(20,personModelReturned.getAge());
    }
}
