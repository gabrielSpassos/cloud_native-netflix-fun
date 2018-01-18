package com.gabrielspassos.poc.controller;

import com.gabrielspassos.poc.model.AccountModel;
import com.gabrielspassos.poc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<AccountModel> getAllAccount(){
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public AccountModel getAccountByCode(@PathVariable("code") String code){
        return accountService.getAccountByCode(code);
    }
}