package com.gabrielspassos.poc.service;


import com.gabrielspassos.poc.connector.PersonServiceConnector;
import com.gabrielspassos.poc.dao.AccountDAO;
import com.gabrielspassos.poc.exception.CodeNotExistException;
import com.gabrielspassos.poc.model.AccountModel;
import com.gabrielspassos.poc.model.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    PersonServiceConnector personServiceConnector;

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setPersonServiceConnector(PersonServiceConnector personServiceConnector) {
        this.personServiceConnector = personServiceConnector;
    }

    public List<AccountModel> getAllAccounts(){
        return accountDAO.getAccountModelList().stream()
                .map(bindAccountModel())
                .collect(Collectors.toList());
    }

    public AccountModel getAccountByCode(String code){
        return accountDAO.getAccountModelList().stream()
                .filter(account -> account.getCode().equals(code))
                .map(bindAccountModel())
                .findFirst()
                .orElseThrow(CodeNotExistException::new);
    }

    private Function<AccountModel, AccountModel> bindAccountModel() {
        return accountModel -> {
            accountModel.getCode();
            accountModel.getBalance();
            accountModel.setPerson(getPersonById(accountModel.getPerson().getId()));
            return accountModel;
        };
    }

    private PersonModel getPersonById(int id){
        return personServiceConnector.run().findPersonById(id);
    }

}
