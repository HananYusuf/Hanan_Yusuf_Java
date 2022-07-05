package com.company.factory.IceCream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IcecreamTest {

    Icecream icecream;
    @Before
    public void setUp()  {
        icecream = new Icecream(); //creating an icecream obj

    }

    @Test

    public void shouldTestIfIcecreamIngrediantHasMilk(){
        boolean expectedOutput = false; //expected output assignment
        List<String> ingredientsList = new ArrayList<>(); //instantiating array list for ingredients
        ingredientsList.add("Milk"); //utilizing add method to add milk to the ingredients list
        icecream.setIngredients(ingredientsList);//new list crated and set as the ingredients property of the icecream
        boolean actualOutput = icecream.isVegan(icecream);
        assertEquals(expectedOutput, actualOutput);
    }

}