package com.librarymanagement.register;

import com.librarymanagement.dao.RegisterationDao;
import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.login.Login;

import java.sql.*;
import java.util.*;
public class Registeration{
    private RegisterationDao registerationDao = new RegisterationDao();
    private ScannerUtility scannerUtility = new ScannerUtility();

    private String userName;
    private String password;

    void insertIntoRegisterTable() throws SQLException {
        checkSpaceInPass(); // Asks the user for Password and checks if there's space in password if so asks them to enter password
        registerationDao.addUserByUsernameAndPassword(userName,password);
    }

    public HashSet allRegisterStudent(ResultSet regTableRow) throws SQLException {
        HashSet<String> allRegStu = new HashSet<>();
        //Map<String,String>
        while(regTableRow.next()) {
            String currentReg = regTableRow.getString("username");
            allRegStu.add(currentReg);
        }
        return allRegStu;
    }
    public void exitCreateAccountOrContinueReg() {
        int ch = -1;
        boolean exitWhile = true;
        System.out.print("Please Enter 0. To Continue Registeration. 1. To Display Log In and Create Account Interface: ");
        while (exitWhile) {
            try {
                ch = scannerUtility.getScanner().nextInt();
            } catch (Exception e) {
                System.out.println("Please Enter either 0 or 1");
                continue;
            }
            switch (ch) {
                case 0:
                    exitWhile = false;
                    break;
                case 1:
                    DisplayInitialInterfaceToStart displayInitialInterfaceToStart = new DisplayInitialInterfaceToStart();
                    displayInitialInterfaceToStart.dispFirstInterface();
                    exitWhile = false;
                    break;
                default:
                    System.out.println("Please Enter either 0 or 1");
                    break;
            }
        }
    }
    public void emptyValueInUserName(){
        while(userName.equals("")) {
            System.out.print("Cannot set Username to Empty. Please enter a Username: ");

            userName = scannerUtility.getScanner().nextLine();
        }
    }
    void checkUniqueUserName() throws SQLException {


            ResultSet regTableRow = registerationDao.getRegisteration(); //

        HashSet<String> allRegStu = allRegisterStudent(regTableRow); // There's member named allRegisterStudent which returns HashSet of username from Register table

        System.out.print("Please enter a Username: ");
        if (!allRegStu.isEmpty()){ // if the hashSet is not empty
            boolean userNameWhileCond = true;

            while(userNameWhileCond){
                userName = scannerUtility.getScanner().nextLine();
                if (userName.equals("")) emptyValueInUserName();

                exitCreateAccountOrContinueReg();

                if (allRegStu.contains(userName)) {
                    System.out.print("Username already exists. Please enter a unique Username: ");
                    userNameWhileCond = true;
                }
                else {
                    insertIntoRegisterTable();
//                    checkSpaceInPass(sc); // Asks the user for Password and checks if there's space in password if so asks them to enter password
//                    String queryToInsInRegTable = "insert into Register (username,password) values ('" + userName + "','" + password + "');";
//                    PreparedStatement pSt = con.prepareStatement(q);
//                    pSt.executeUpdate();
                    userNameWhileCond = false; // stoping the l
                }
            }
        }
        else { // If hashSet is empty
            userName = scannerUtility.getScanner().nextLine();
            insertIntoRegisterTable(); // Updating Register Table

        }
    }
    void checkSpaceInPass(){

        boolean spaceInPass = true;
        while(spaceInPass) {
            System.out.print("Please enter a password: ");
            password = scannerUtility.getScanner().nextLine();
            int len = password.length();

            while(len == 0) {
                System.out.print("\nCannot set password empty.");
                System.out.print("\nPlease enter a password:" );
                password = scannerUtility.getScanner().nextLine();
                len = password.length();
            }

            for (int i = 0; i < len; i++) {
                if (password.charAt(i) == ' ') {
                    System.out.println("Space Not allowed in password. Please re-enter a password without space.");
                    break;
                }
                else if(i == len-1) spaceInPass = false;
            }
        }
    }
    public void register () throws Exception{
        checkUniqueUserName(); // checks if the username is unique or not.
//        checkSpaceInPass(sc); // Asks the user for Password and checks if there's space in password if so asks them to enter password

        System.out.println("\nYou have Succesfully Registered into the Library " + userName + "!\n");

        DisplayInitialInterfaceToStart displayInitialInterfaceToStart= new DisplayInitialInterfaceToStart();
        displayInitialInterfaceToStart.dispFirstInterface();
//        Login loginObj = new Login();
//        loginObj.logInLogic();
    }
}