package com.librarymanagement.returnbook;

import com.librarymanagement.dao.LibraryBookDao;
import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.dao.ReturnBookDao;
import com.librarymanagement.model.LibraryBooks;
import com.librarymanagement.model.ReturnABook;

import java.util.List;

public class ReturnBook {
    private static ReturnBookDao returnBookDao = new ReturnBookDao();
    private LibraryBookDao libraryBookDao = new LibraryBookDao();
    private String returnBookName;
    private boolean returnedBook = false;
    private String userName = System.getProperty("username");
    private ScannerUtility sc = new ScannerUtility();

    public void returnBook(){
        System.out.print("Name the book you want to return: ");
        returnBookName = sc.getScanner().nextLine();

        System.setProperty("returnBookName", returnBookName); // returnBook accessible everywhere

        List<ReturnABook> issuedBookList = ReturnBookDao.getIssuedBookDetails();

        for(ReturnABook issuedBook: issuedBookList){
//            System.out.println("Inside");1
            if(issuedBook.getUserName().equals(userName) && issuedBook.getIssuedBook().equals(returnBookName)){
                returnBookDao.returnBook();
                returnBookDao.decreaseSerial(issuedBook.getSerial());
                libraryBookDao.increaseBookCountAfterReturn(returnBookName);

                System.out.println("\n" + returnBookName + " successfully returned back to the Library.\n");
                returnedBook = true;
                break;
            }
        }

        if(!returnedBook) System.out.println("You have not issued " + returnBookName + " book.\n");
    }
}
