package com.company.M2ChallengeYusufHanan.controller;

import com.company.M2ChallengeYusufHanan.exceptions.NotFoundException;
import com.company.M2ChallengeYusufHanan.model.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MathSolutionController {



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution addTwoInt(@RequestBody @Valid MathSolution operation ) {//MathSolution obj that returns a Json obj
        operation.setOperation("add"); //set the operation and call the method
        operation.add();//calling the add method
               return operation;

    }

    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution subTwoInt(@RequestBody @Valid MathSolution operation ) {
        operation.setOperation("subtract");
        operation.subtract();
              return operation;

    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution mulTwoInt(@RequestBody @Valid MathSolution operation ) {
     operation.setOperation("multiply");
     operation.multiply();
           return operation;
    }

    @RequestMapping(value = "/division", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public MathSolution divTwoInt(@RequestBody @Valid MathSolution operation) {
    operation.setOperation("division");
    operation.divide();

        if(operation.getOperand2()==0) {
            throw new ArithmeticException("You cannot divide by zero");
        } else {
            operation.setOperation("division");
            operation.divide();
        }
        return operation;

    }
}
