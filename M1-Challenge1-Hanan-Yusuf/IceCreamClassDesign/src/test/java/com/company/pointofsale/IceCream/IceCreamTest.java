package com.company.pointofsale.IceCream;

import com.company.factory.IceCream.Icecream;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IceCreamTest {

    IceCream icecream;
    @Before
    public void setUp()  {
        icecream = new IceCream(); //icecream obj crated

    }

    @Test

    public void shouldCalculateTotalCost(){
        int expectedOutput = 5; //expected output assignment
        icecream.setPrice(2);
        icecream.setQuantity(2);
        icecream.setNumberOfToppings(1);
        int actualOutput = icecream.calculateCost();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test

    public void shouldThisMonthsSpecial(){
        IceCream iceCream2 = new IceCream();
        int expectedOutput = 100; //expected output assignment
        icecream.setPrice(2);
        icecream.setQuantity(50);
        icecream.setSoldAmount(100);
        int actualOutput = icecream.getSoldAmount();
        assertEquals(expectedOutput, actualOutput);
    }

}