package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class ConverterIf implements Converter{

    @Override
    public String convertMonth(int monthNumber) {
        if (monthNumber > 12 || monthNumber <1){
            return "Invalid month number!";
        }

        if(monthNumber == 1){
            return "JANUARY";
        } else if (monthNumber == 2) {
            return "FEBRUARY";
        }else if (monthNumber == 3) {
            return "MARCH";
        }else if (monthNumber == 4) {
            return "APRIL";
        }else if (monthNumber == 5) {
            return "MAY";
        }else if (monthNumber == 6) {
            return "JUNE";
        }else if (monthNumber == 7) {
            return "JULY";
        }else if (monthNumber == 8) {
            return "AUGUST";
        }else if (monthNumber == 9) {
            return "SEPTEMBER";
        }else if (monthNumber == 10) {
            return "OCTOBER";
        }else if (monthNumber == 11) {
            return "NOVEMBER";
        }else {
            return "DECEMBER";
        }
    }

    @Override
    public String convertDay(int dayNumber) {
        if (dayNumber > 7 || dayNumber < 1) {
            return "not a real day";
        }

        if (dayNumber == 1) {
            return "SUNDAY";
        } else if (dayNumber == 2) {
            return "MONDAY";
        } else if (dayNumber == 3) {
            return "TUESDAY";
        } else if (dayNumber == 4) {
            return "WEDNESDAY";
        } else if (dayNumber == 5) {
            return "THURSDAY";
        } else if (dayNumber == 6) {
            return "FRIDAY";
        } else {
            return "SATURDAY";
        }
    }

    }
