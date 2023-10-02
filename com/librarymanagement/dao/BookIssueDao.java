package com.librarymanagement.dao;

import com.librarymanagement.config.JdbcConnection;
import com.librarymanagement.model.BookIssue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookIssueDao {
    private static final String GET_STUDENT = "select books from issuedBooks where username = '%s';";

    private static final String INSERT_BOOK_INTO_ISSUEDBOOK = "insert into issuedbooks (username,books) values ('%s','%s');";

//    private
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private JdbcConnection con = new JdbcConnection();

    public ResultSet getStudent(){
        try {
            resultSet = con.getStatement().executeQuery(String.format(GET_STUDENT,System.getProperty("username")));
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            System.out.println("Error occured during executing GET_STUDENT query");
        }
        return resultSet;
    }
//    ListOfIssuedBookByTheUser= new ArrayList<>();
//    public List<BookIssue> getIssuedBook(){
//    ListOfIssuedBookByTheUser= new ArrayList<>();
//
//    }

    public void updateStudentByBookName(String userName,String libBookName){
        try{
            preparedStatement = con.getCon().prepareStatement(String.format(INSERT_BOOK_INTO_ISSUEDBOOK,userName,libBookName));
            preparedStatement.executeUpdate();
//            System.out.println("Updated Student.");
        } catch(Exception e){
            System.out.println("Error occured while updating student by book name.");
        }
    }



}
