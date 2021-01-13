package com.softserve.itacademy.model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.params.provider.Arguments;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StateTest {
    private static State validState;
    private static Task validTask;
    @BeforeAll
    static void init(){
        validState = new State();
        validState.setName("Valid-Name");
        validTask = new Task();
        validTask.setName("Valid-Task");
        validTask.setPriority(Priority.MEDIUM);
        validTask.setState(validState);
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
    void constraintViolationOnEmptyStateName() {
        State emptyState = new State();
        emptyState.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(emptyState);
        assertEquals(1, violations.size());
    }
    @Test
    void constraintViolationOnNullStateName() {
        State nullState = new State();
        nullState.setName(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(nullState);
        assertEquals(1, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStateName")
    void constraintViolationInvalidName(String input, String errorValue) {
        State state = new State();
        state.setName(input);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidStateName(){
        return Stream.of(
                Arguments.of("-", "-"),
                Arguments.of("b", "b"),
                Arguments.of("11", "11")
        );
    }
}
