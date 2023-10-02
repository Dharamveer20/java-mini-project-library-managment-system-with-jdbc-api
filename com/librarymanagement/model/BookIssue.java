package com.librarymanagement.model;

public class BookIssue {
    private int serial;
    private String userName;
    private String bookIssuedName;

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

    public String getBookIssuedName() {
        return bookIssuedName;
    }

    public void setBookIssuedName(String bookIssuedName) {
        this.bookIssuedName = bookIssuedName;
    }

    @Override
    public String toString() {
        return "BookIssue{" +
                "serial=" + serial +
                ", userName='" + userName + '\'' +
                ", bookIssuedName='" + bookIssuedName + '\'' +
                '}';
    }
}
