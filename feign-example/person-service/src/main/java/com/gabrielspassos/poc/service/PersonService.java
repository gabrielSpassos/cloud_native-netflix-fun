package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.dao.PersonDAO;
import com.gabrielspassos.poc.exception.IdNotExistException;
import com.gabrielspassos.poc.model.PersonModel;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonDAO personDAO = new PersonDAO();
    private PersonModel person = new PersonModel();

    public PersonService() {
        personDAO.populateList();
    }

    public PersonModel getPersonById(int id) throws IdNotExistException {
        for(int i=0; i<personDAO.getPeopleList().size(); i++) {
            if(personDAO.getPeopleList().get(i).getId() == id){
                person.setId(id);
                person.setName(personDAO.getPeopleList().get(i).getName());
                person.setLastName(personDAO.getPeopleList().get(i).getLastName());
                person.setAge(personDAO.getPeopleList().get(i).getAge());
                return person;
            }
        }
        throw new IdNotExistException();
    }
}
