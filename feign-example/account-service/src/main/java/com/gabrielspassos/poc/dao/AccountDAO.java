package com.gabrielspassos.poc.dao;

import com.gabrielspassos.poc.model.AccountModel;
import com.gabrielspassos.poc.model.PersonModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAO {

    List<AccountModel> accountModelList = new ArrayList<>();

    public AccountDAO() {
        accountModelList.add(new AccountModel("0001",5000.50,new PersonModel(1)));
        accountModelList.add(new AccountModel("0002",7080.90,new PersonModel(3)));
        accountModelList.add(new AccountModel("0003",10054.90,new PersonModel(4)));
        accountModelList.add(new AccountModel("0004",80.50,new PersonModel(2)));
        accountModelList.add(new AccountModel("0005",999990.50,new PersonModel(5)));
    }

    public List<AccountModel> getAccountModelList() {
        return accountModelList;
    }
}
