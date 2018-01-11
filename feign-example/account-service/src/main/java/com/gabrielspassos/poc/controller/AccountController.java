package com.gabrielspassos.poc.controller;

import com.gabrielspassos.poc.exception.CodeNotExistException;
import com.gabrielspassos.poc.model.AccountModel;
import com.gabrielspassos.poc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AccountModel> getAllAccount(){
        return accountService.getAllAccount();
    }

    @RequestMapping(value = "/{code}",method = RequestMethod.GET)
    public AccountModel getAccountByCode(@PathVariable("code") String code) throws CodeNotExistException {
        return accountService.getAccountByCode(code);
    }
}
