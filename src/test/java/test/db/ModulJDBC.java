package test.db;

import java.sql.*;
import java.util.ArrayList;

public class ModulJDBC {

    private int id;
    private String username;
    private String email;
    private String password;
    private String type;

    public ModulJDBC(int id, String username, String email, String password, String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public ModulJDBC() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }






}