package com.starichenkov.Model;

public interface IModel {

    void createUser(String fio, String mail, String password);

    void findUser(String mail, String password);
}
