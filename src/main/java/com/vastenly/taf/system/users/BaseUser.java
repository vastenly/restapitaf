package com.vastenly.taf.system.users;

public class BaseUser {
    private String login;
    private String password;

    public BaseUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "email='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
