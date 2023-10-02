package com.librarymanagement.config;

import java.sql.*;

public class JdbcConnection {
    public Connection getCon()  {
        Connection con = null;
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dharam","root", "anappleaday.?@20");

        } catch(Exception e){
            System.out.println("Connection not established");
        }
        return con;
    }
    public Statement getStatement(){
        try {
            return getCon().createStatement();
        } catch (SQLException e) {
            System.out.println("Statement not created");
//            throw new RuntimeException(e);
        }
        return null;
    }


}
