package com.librarymanagement.dao;

import com.librarymanagement.config.JdbcConnection;
import com.librarymanagement.model.LibraryBooks;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryBookDao {
    private JdbcConnection con = new JdbcConnection();
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
//    private final Connection con = con.getCon();
    private static final String GET_BOOKNAME_AND_BOOK_COUNT_QUERY = "select * from Books;";
    private static final String UPDATE_BOOK_BY_COUNT_AND_NAME_QUERY = "update books set bookcount = %d where name = '%s';";

    private static final String INCREASE_BOOK_COUNT_BY_1_AFTER_RETURNED = "update books set bookcount = bookcount+1 where name = '%s';";


    public List<LibraryBooks> getBook(){
        List<LibraryBooks> libraryBooksList = new ArrayList<>();
        try  {
            resultSet = con.getStatement().executeQuery(GET_BOOKNAME_AND_BOOK_COUNT_QUERY);
            while(resultSet.next()){
                LibraryBooks book = new LibraryBooks();

                book.setBookName(resultSet.getString("name"));
                book.setAuthorName(resultSet.getString("author"));
                book.setBookCount(resultSet.getInt("bookcount"));
                book.setId(resultSet.getInt("id"));
                libraryBooksList.add(book);
            }
        } catch(Exception e){
            System.out.println("ResultSet not created");
        }
        return libraryBooksList;
    }
    public void updateBookByCountAndName(int bookCount, String bookName){
        try{
            preparedStatement = con.getCon().prepareStatement(String.format(UPDATE_BOOK_BY_COUNT_AND_NAME_QUERY,bookCount,bookName));
            preparedStatement.executeUpdate();
//            System.out.println("Succesfully Updated Book.");
        } catch(Exception e){
            System.out.println("Error occured while Updating book.");
        }
    }
    public void increaseBookCountAfterReturn(String returnBookName){
        try{
            resultSet = con.getStatement().executeQuery(GET_BOOKNAME_AND_BOOK_COUNT_QUERY);
            while(resultSet.next()){
                if(resultSet.getString("name").equals(returnBookName)){
                    preparedStatement = con.getCon().prepareStatement(String.format(INCREASE_BOOK_COUNT_BY_1_AFTER_RETURNED,returnBookName));
                    preparedStatement.executeUpdate();
                }
            }
        }catch(Exception e){
            System.out.println("Error while increasing book count after return");
            System.out.println(e);
        }
    }

}
