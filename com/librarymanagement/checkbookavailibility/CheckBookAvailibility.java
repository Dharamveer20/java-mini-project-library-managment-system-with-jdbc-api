package com.librarymanagement.checkbookavailibility;

import com.librarymanagement.dao.LibraryBookDao;
import com.librarymanagement.helper.ScannerUtility;
import com.librarymanagement.model.LibraryBooks;

import java.util.List;

public class CheckBookAvailibility {
    private LibraryBookDao libraryBookDao = new LibraryBookDao();
    private ScannerUtility scannerUtility = new ScannerUtility();
    private String bookName;
    private boolean bookAvailable = false;

    public void checkingAvailOfBook(){
        System.out.print("Please Enter the Book name: ");
        bookName = scannerUtility.getScanner().nextLine();

        List<LibraryBooks> libraryBooksList = libraryBookDao.getBook();
        for(LibraryBooks books: libraryBooksList){
            if(bookName.equals(books.getBookName())) {
                if(books.getBookCount() == 0){
                    System.out.println("\nYes, " + bookName + " book is available in our Library. Though all the books are issued by the Students. Please wait for them to return.\n");
                } else {
                    System.out.println("\nYes, " + bookName + " book is available in our Library.\n");
                }
                bookAvailable = true;
                break;
            }
        }
        if(!bookAvailable) System.out.println("\nNo, " + bookName + " book is not available in our Library.\n");
    }
}
