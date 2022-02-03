package itmo.web;

import itmo.web.dao.entities.DotEntity;

import java.util.LinkedList;

public class ResponseWrapper {
    private boolean status;
    private String username;
    private String password;
    private LinkedList<DotEntity> dotlist;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<DotEntity> getDotlist() {
        return dotlist;
    }

    public void setDotlist(LinkedList<DotEntity> dotlist) {
        this.dotlist = dotlist;
    }
}
