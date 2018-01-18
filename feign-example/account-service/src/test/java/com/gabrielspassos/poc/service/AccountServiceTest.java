package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.connector.PersonServiceConnector;
import com.gabrielspassos.poc.dao.AccountDAO;
import com.gabrielspassos.poc.exception.CodeNotExistException;
import com.gabrielspassos.poc.model.AccountModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {

    AccountService accountService;

    @Before
    public void setup(){
        accountService = new AccountService();
    }

    @Test
    public void mustReturnOneAccount(){
        accountService.setAccountDAO(new AccountDAO());
        accountService.setPersonServiceConnector(new PersonServiceConnector());
        AccountModel accountReturned = accountService.getAccountByCode("0001");
        assertEquals("0001",accountReturned.getCode());
        assertEquals(5000.5,accountReturned.getBalance(),0);
        assertEquals(1,accountReturned.getPerson().getId());
        assertEquals("Gabriel",accountReturned.getPerson().getName());
        assertEquals("Passos",accountReturned.getPerson().getLastName());
        assertEquals(20,accountReturned.getPerson().getAge());

    }

    @Test(expected = CodeNotExistException.class)
    public void mustThrowException(){
        accountService.setAccountDAO(new AccountDAO());
        accountService.getAccountByCode("9999");
    }
}