package com.company.M2ChallengeYusufHanan.controller;

import com.company.M2ChallengeYusufHanan.exceptions.NotFoundException;
import com.company.M2ChallengeYusufHanan.model.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathSolutionController {



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution addTwoInt(@RequestBody MathSolution operation ) {//MathSolution obj that returns a Json obj
        operation.setOperation("add"); //set the operation and call the method
        operation.add();//calling the add method
               return operation;

//        if(operation.getOperand1() == null || ){
//            throw new NotFoundException("operand not found/not a number in collection");
//        }
    }

    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution subTwoInt(@RequestBody MathSolution operation ) {
        operation.setOperation("subtract");
      operation.subtract();
        return operation;

    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution mulTwoInt(@RequestBody MathSolution operation ) {
        operation.setOperation("multiply");
     operation.multiply();
        return operation;
    }

    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution divTwoInt(@RequestBody MathSolution operation) {
        operation.setOperation("divide");
    operation.divide();
        return operation;
    }
}
