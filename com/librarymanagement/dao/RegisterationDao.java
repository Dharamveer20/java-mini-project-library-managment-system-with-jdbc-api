package com.librarymanagement.dao;

import com.librarymanagement.config.JdbcConnection;

import java.sql.*;

public class RegisterationDao {
    private JdbcConnection con = new JdbcConnection();
    private static final String GET_REGISTER = "Select * from Register";
    private static final String ADD_BY_USERNAME_AND_PASSOWRD = "insert into Register (username,password) values ('%s', '%s');";
    public ResultSet getRegisteration() throws SQLException { //

        Statement regTable = con.getCon().createStatement();
        ResultSet regTableRow = regTable.executeQuery(GET_REGISTER);
        return regTableRow;
    }


    public void addUserByUsernameAndPassword(String userName, String password) {
        try {
            PreparedStatement pSt = con.getCon().prepareStatement(String.format(ADD_BY_USERNAME_AND_PASSOWRD, userName, password));
            pSt.executeUpdate();
            System.out.println("Succesfully added into Register table");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error occured while Inserting into Register.");
        }
    }
}
