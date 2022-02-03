package itmo.web.model;

import itmo.web.ResponseWrapper;

public class UserManager {

    public boolean login(String username, String password){

        return (username.length()>10);
    }
    public boolean signUp(String username, String password){

        return (username.length()>10);
    }
}
