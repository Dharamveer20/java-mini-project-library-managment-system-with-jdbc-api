package com.librarymanagement.register;

import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.login.Login;

import java.util.Scanner;

public class DisplayInitialInterfaceToStart {

    public DisplayInitialInterfaceToStart(){
        scannerUtility =  new ScannerUtility();
         regObj = new Registeration();
    }

    private ScannerUtility scannerUtility;
    private Registeration regObj;

    public void dispFirstInterface() {
        Scanner sc = scannerUtility.getScanner();
        boolean whileCondForLogAndRegDis = true;
        int choice = 0;
        System.out.println("1. Log in 2. Create Account");

        while(whileCondForLogAndRegDis){
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Please enter a valid number either 1 or 2 inorder to perform appropriate function.");
                System.out.println("1. Log in 2. Create Account");
                sc.nextLine();
                continue;
            }
            switch (choice){
                case 1:
                    try {
                        Login lgObj = new Login();
                        lgObj.logInLogic(); // Asking username and pass if correct shows 1. Issue book waala interface
                    } catch(Exception e){
                        System.out.println("Error occured while trying to Log In. Please try again later.");
                    }
                    break;

                case 2:
                    try {
                        regObj.register(); // Ask for new username and pass. If registered shows this switch case again.
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
//                    System.out.println("Register");
                    break;

                default:
                    System.out.println("Please enter a valid number either 1 or 2 inorder to perform appropriate function.");
                    System.out.println("1. Log in 2. Create Account");
                    break;
            }
        }
    }

}
