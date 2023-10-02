package com.librarymanagement.bookissue;

import com.librarymanagement.dao.LibraryBookDao;
import com.librarymanagement.dao.BookIssueDao;
import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.model.LibraryBooks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueBook{
    String bName;
    private int bookCount;
    boolean bookAlreadyIssued = false;
    private LibraryBookDao libraryBookDao = new LibraryBookDao();
    private BookIssueDao studentDao = new BookIssueDao();

    private ScannerUtility scannerUtility = new ScannerUtility();

    private LibraryBookDao bookDao = new LibraryBookDao();
    public void issueOneBook() {

        try{
        ResultSet bookIssedByUserResultSet = studentDao.getStudent();
        List<LibraryBooks> booksList = bookDao.getBook();
        ArrayList<String> issuedBooksOfStu = new ArrayList<>();

        while(bookIssedByUserResultSet.next()){
//            String stuIssuedBookName = ResultSet.getString("book");
            issuedBooksOfStu.add(bookIssedByUserResultSet.getString("books"));
        }
        System.out.print("Name the book you want to issue: ");

        bName = scannerUtility.getScanner().nextLine();

        for(int i=0; i < issuedBooksOfStu.size(); i++) {
            String currentIterationIssuedBookName = issuedBooksOfStu.get(i);
            if (currentIterationIssuedBookName.equals(bName)) {
                System.out.println("\nYou have already issued " + currentIterationIssuedBookName + " books.\n");
                bookAlreadyIssued = true;
                break;
            }
        }
//        ResultSet bookTable = st.executeQuery(bookQuery);
        boolean libBookEqualReqBook = false;

        if (!bookAlreadyIssued) {
            for (LibraryBooks book : booksList) {
                if (bName.equals(book.getBookName())) {
                    if(book.getBookCount() == 0){
                        System.out.println("All the " + bName + " books present in our library is issued by other students.Please wait for them to return.\n");
                        libBookEqualReqBook = true;
                    }
                    else {
                        studentDao.updateStudentByBookName(System.getProperty("username"), book.getBookName());
                        System.out.println(System.getProperty("username") + ", you have issued " + book.getBookName() + " book from the Library.\n");

                        // Decreased Number of books from the Book table by 1
                        bookDao.updateBookByCountAndName(book.getBookCount() - 1, book.getBookName());
                        libBookEqualReqBook = true;
                        break;
                    }
                }
            }
        }
//        else if()){for (LibraryBooks book : booksList) {
//        }
        else{
            libBookEqualReqBook = true;
        }

        if (!libBookEqualReqBook) System.out.println("\nThe Book " + bName + " you requested to Issue is not present in our Library.\n");
    } catch(Exception e) {
            System.out.println(e);
            System.out.println("Error Occured while issuing the book.");
        }
    }
}