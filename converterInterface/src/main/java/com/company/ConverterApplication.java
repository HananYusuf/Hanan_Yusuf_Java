package com.company;

public class ConverterApplication {
    public static void main(String[] args) {

        Converter conv1 = new ConverterIf();
        Converter conv2 = new ConverterSwitch();

//        conv1.convertMonth(1);
//        conv1.convertMonth(1);
        System.out.println(conv1.convertMonth(1));
        System.out.println(conv1.convertDay(1));

        System.out.println(conv2.convertMonth(2));
        System.out.println(conv2.convertDay(2));
    }
}
