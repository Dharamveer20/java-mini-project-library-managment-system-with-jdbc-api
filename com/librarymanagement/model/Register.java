package com.librarymanagement.model;

public class Register {
    private int serial;
    private String userName;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Register{" +
                "serial=" + serial +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
