package com.librarymanagement.login;

import com.librarymanagement.dao.RegisterationDao;
import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.register.DisplayInitialInterfaceToStart;
import com.librarymanagement.register.Registeration;

import java.sql.*;
import java.util.*;

public class Login { //
    private String userName;
    private String password;
    private Registeration registerObj;
   // private DisplayLogAndRegisteration dislgAndRegObj;
    private RegisterationDao registerationDao;
    private ScannerUtility scannerUtility;
    private LoggedIn logInObj;

    public  Login(){
        registerObj = new Registeration();
        //dislgAndRegObj = new DisplayLogAndRegisteration();
        registerationDao = new RegisterationDao();
        scannerUtility = new ScannerUtility();
        logInObj = new LoggedIn();
    }
    public void logInLogic() throws Exception {
        DisplayInitialInterfaceToStart dislgAndRegObj = new DisplayInitialInterfaceToStart();

        Scanner sc = scannerUtility.getScanner();
        System.out.print("Please Enter your Username: ");
        userName = sc.nextLine();
        System.out.print("Please Enter your Password: ");
        password = sc.nextLine();

        ResultSet regTableRow = registerationDao.getRegisteration(); // There's member named RowsOfRegisterTable in Registeration class which returns resultSet of Register table
//        int rows = regTableRow.getFetchSize();
//        ResultSet copyRegTableRow = regTableRow;
      // HashSet<String> allRegStu = registerObj.allRegisterStudent(regTableRow); // There's member named allRegisterStudent in Registeration class which returns HashSet of username from Register table
       // regTableRow.next();
//        while(regTableRow.next()) {
//            String user = regTableRow.getString("username");
//            String pass = regTableRow.getString("password");
//            System.out.println(user + " " + pass);
//        }
        if(regTableRow.next()){ // if Register table is not empty;
            boolean exitWhile = true;
            boolean isLoginSuccess = false;
//            while(regTableRow.next()){
            while(exitWhile){
                String user = regTableRow.getString("username");
                String pass = regTableRow.getString("password");

                if (user.equals(userName) && pass.equals(password)){
                    isLoginSuccess = true;
                    System.out.println("\nYou Logged in succesfully " + userName + "!\n");
                    System.setProperty("username",userName);
                    logInObj.printSwitch(); // Displays 1.Issue a book 2. Return a book waala interface.
                    break;
                }
                exitWhile = regTableRow.next();
            }
            if(!isLoginSuccess){
                System.out.println("Wrong Username or Password!\n");
                dislgAndRegObj.dispFirstInterface(); // Displaying Log in and Register Interface
            }
        }
        else{
            System.out.println("Wrong Username or Password!\n");
            dislgAndRegObj.dispFirstInterface(); // Displaying Log in and Register Interface
        }
    }
}
