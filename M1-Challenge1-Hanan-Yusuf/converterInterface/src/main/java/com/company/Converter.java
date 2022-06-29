package com.company;

public interface Converter {

    //all methods in an interface are public
    //interface refers to the methods not the classes
    String convertMonth(int monthNumber);//params to return

    String convertDay(int dayNumber);

}
