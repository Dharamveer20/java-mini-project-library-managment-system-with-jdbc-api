package com.librarymanagement.model;

public class ReturnABook {
    private int serial;
    private String userName;
    private String issuedBook;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIssuedBook() {
        return issuedBook;
    }

    public void setIssuedBook(String issuedBook) {
        this.issuedBook = issuedBook;
    }
}

