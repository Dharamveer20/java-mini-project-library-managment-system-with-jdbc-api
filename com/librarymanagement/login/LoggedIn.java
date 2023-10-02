package com.librarymanagement.login;

import com.librarymanagement.bookissue.IssueBook;
import com.librarymanagement.checkbookavailibility.CheckBookAvailibility;
import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.register.DisplayInitialInterfaceToStart;
import com.librarymanagement.returnbook.ReturnBook;

import java.sql.*;

public class LoggedIn {
    private ScannerUtility scannerUtility = new ScannerUtility();
    void printSwitch() throws Exception {
        boolean loggedIn = true;
            int ch = 0;
//            boolean whileCondition = true;
            while (loggedIn){
                System.out.print("1.Issue a Book 2. Return a book 3.Check Book Availability 4.Log Out.\nEnter what you want to Perform: ");
                try {
                    ch = scannerUtility.getScanner().nextInt();
//                    scannerUtility.getScanner().nextLine();
                } catch (Exception e) {
                    System.out.println("\nPlease enter a valid number between 1 to 4 inorder to perform appropriate function.\n");
//                    scannerUtility.getScanner().nextLine();
                    continue;
                }
                switch (ch) {
                    case 1:
//                          System.out.println("hey");
//                        Book();
                        IssueBook ib = new IssueBook();
                        ib.issueOneBook();
                        break;
                    case 2:
                        ReturnBook returnB = new ReturnBook();
                        returnB.returnBook();
                        break;
                    case 3:
                        CheckBookAvailibility checkBookAvail = new CheckBookAvailibility();
                        checkBookAvail.checkingAvailOfBook();
                        break;
                    case 4:
                        System.out.println("\nYou Logged Out Successfully!\n");
                        DisplayInitialInterfaceToStart displayLogInAndReg = new DisplayInitialInterfaceToStart();
                        displayLogInAndReg.dispFirstInterface();

                        loggedIn = false;
                        break;
                }
            }

    }
}
