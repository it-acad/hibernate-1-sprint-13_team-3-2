package com.softserve.itacademy.model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StateTest {
    private static State validState;
    @BeforeAll
    static void init(){
        validState = new State();
        validState.setName("Valid-Name");
    }

    @Test
    void RoleWithValidName(){
        State state = new State();
        state.setName("Valid-Name");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);

        assertEquals(0, violations.size());
    }
    @Test
    void constraintViolationOnEmptyRoleName() {
        State emptyState = new State();
        emptyState.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(emptyState);
        assertEquals(1, violations.size());
    }

    @ParameterizedTest
    void constraintViolationOnSizeStateName(String input, String errorValue){
        State state = new State();
        state.setName(input);
    }
}
