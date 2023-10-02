package com.librarymanagement;

import com.librarymanagement.config.JdbcConnection;
import com.librarymanagement.register.DisplayInitialInterfaceToStart;

import java.util.*;


public class LibraryManagementApplication {
    private static JdbcConnection jdbcConnection = new JdbcConnection();
    public static void main(String [] args){
        try{
            // Displaying Please Log in or Register
            Scanner sc = new Scanner(System.in);

            // Displaying Log in and Register Interface
            DisplayInitialInterfaceToStart obj = new DisplayInitialInterfaceToStart();
            obj.dispFirstInterface();

            System.out.println("Thank you for coming in our Library!");
        } catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
