package com.company.M2ChallengeYusufHanan.controller;
import com.company.M2ChallengeYusufHanan.exceptions.NotFoundException;
import com.company.M2ChallengeYusufHanan.model.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class MonthController {
    private static List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month("JANUARY", 1),
            new Month("FEBRUARY", 2),
            new Month("MARCH", 3),
            new Month("APRIL", 4),
            new Month("MAY", 5),
            new Month("JUNE", 6),
            new Month("JULY", 7),
            new Month("AUGUST", 8),
            new Month("SEPTEMBER", 9),
            new Month("OCTOBER", 10),
            new Month("NOVEMBER", 11),
            new Month("DECEMBER", 12)
    ));

    @RequestMapping(value = "/month/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Month getMonth(@PathVariable int monthNumber) {//returning a month obj
        Month foundMonth = null;
             if(monthNumber < 1 || monthNumber > 12){
                 throw new NotFoundException("Month not found in collection");
             }

        for (Month month : monthList) {//created a var month on the month obj
            if (month.getNumber() == monthNumber) {//iterating through the month list and doing a comparison
                foundMonth = month;
                break;
            }

        }
        return foundMonth;
    }

    @RequestMapping(value = "/randomMonth", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)

    public Month getRandomMonth() {//returning Month obj
        Random monthNumber = new Random();
        //bound is a boundary cause 13th month doesn't exist
       return monthList.get(monthNumber.nextInt(12));//generating random num. it should select among 13 items

    }

}
