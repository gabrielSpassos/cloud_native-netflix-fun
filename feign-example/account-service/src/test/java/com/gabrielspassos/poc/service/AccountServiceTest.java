package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.connector.PersonServiceConnector;
import com.gabrielspassos.poc.dao.AccountDAO;
import com.gabrielspassos.poc.exception.CodeNotExistException;
import com.gabrielspassos.poc.model.AccountModel;
import com.gabrielspassos.poc.model.PersonModel;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class AccountServiceTest {

    AccountService accountService;

    @Before
    public void setup(){
        accountService = new AccountService();
    }

    @Test
    public void mustReturnAccoutCode1() throws CodeNotExistException {
        accountService.setAccountDAO(new AccountDAO());
        accountService.setPersonServiceConnector(new PersonServiceConnector());
        AccountModel accountModelReturned = accountService.getAccountByCode("0001");
        PersonModel personModelReturned = accountModelReturned.getPerson();
        assertEquals("0001",accountModelReturned.getCode());
        assertEquals(5000.5,accountModelReturned.getBalance(),0);
        assertEquals(1,personModelReturned.getId());
        assertEquals("Gabriel",personModelReturned.getName());
        assertEquals("Passos",personModelReturned.getLastName());
        assertEquals(20,personModelReturned.getAge());
    }

    @Test
    public void mustThrowException(){
        try{
            accountService.setAccountDAO(new AccountDAO());
            accountService.setPersonServiceConnector(new PersonServiceConnector());
            AccountModel accountModelReturned = accountService.getAccountByCode("9999");
        } catch (CodeNotExistException e) {
            assertTrue(true);
        }
    }


}
