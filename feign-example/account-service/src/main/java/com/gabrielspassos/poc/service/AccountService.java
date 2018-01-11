package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.connector.PersonServiceConnector;
import com.gabrielspassos.poc.dao.AccountDAO;
import com.gabrielspassos.poc.exception.CodeNotExistException;
import com.gabrielspassos.poc.model.AccountModel;
import com.gabrielspassos.poc.model.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    PersonServiceConnector personServiceConnector;

    List<AccountModel> accountList = new ArrayList<>();

    PersonModel person = new PersonModel();

    public List<AccountModel> getAllAccount(){
        for (int i = 0; i <accountDAO.getAccountModelList().size() ; i++) {
            person =  getPersonByAccount(accountDAO.getAccountModelList().get(i).getPerson().getId());
            accountDAO.getAccountModelList().get(i).setPerson(person);
            accountList.add(accountDAO.getAccountModelList().get(i));
        }
        return accountList;
    }

    public AccountModel getAccountByCode(String code) throws CodeNotExistException {
        for (int i = 0; i < accountDAO.getAccountModelList().size(); i++) {
            if(accountDAO.getAccountModelList().get(i).getCode().equals(code)){
                person =  getPersonByAccount(accountDAO.getAccountModelList().get(i).getPerson().getId());
                System.out.println(person.getName() + person.getLastName() + person.getId() + person.getAge());
                accountDAO.getAccountModelList().get(i).setPerson(person);
                return accountDAO.getAccountModelList().get(i);
            }
        }

        throw new CodeNotExistException();
    }

    private PersonModel getPersonByAccount(int id){
        return personServiceConnector.run().findPersonById(id);
    }
}
