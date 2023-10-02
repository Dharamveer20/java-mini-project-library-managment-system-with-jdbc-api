package com.librarymanagement.dao;

import com.librarymanagement.config.JdbcConnection;
import com.librarymanagement.model.ReturnABook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookDao {
    private static JdbcConnection con = new JdbcConnection();
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    private static final String DELETE_FROM_ISSUEDBOOKS = "delete from issuedBooks where username = '%s' and books = '%s';";
    private static final String GET_ALL_FROM_ISSUEDBOOK_QUERY = "select * from issuedBooks;";

    private static final String DECREASE_SERIAL_BY_1 = "update issuedBooks set serial = serial - 1 where serial > %d;";
    public static List<ReturnABook> getIssuedBookDetails(){
        List<ReturnABook> issuedBooksList = new ArrayList<>();
        try  {
            resultSet = con.getStatement().executeQuery(GET_ALL_FROM_ISSUEDBOOK_QUERY);
            while(resultSet.next()){
                ReturnABook book = new ReturnABook();

                book.setSerial(resultSet.getInt("serial"));
                book.setUserName(resultSet.getString("username"));
                book.setIssuedBook(resultSet.getString("books"));
                issuedBooksList.add(book);
            }
        } catch(Exception e){
            System.out.println(e);
            System.out.println("ResultSet not created.");
        }
        return issuedBooksList;
    }

    public static void returnBook(){
        try{
            preparedStatement = con.getCon().prepareStatement(String.format(DELETE_FROM_ISSUEDBOOKS, System.getProperty("username"), System.getProperty("returnBookName")));
            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("\nError occured while returning the book. Please try again later.\n");
        }
    }
    public static void decreaseSerial(int serial){
        try{
            preparedStatement = con.getCon().prepareStatement(String.format(DECREASE_SERIAL_BY_1,serial));
            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("error at updating serial.");
        }
    }
}
