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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootTest
public class ToDoTest {

    private static Role traineeRole;
    private static User user;
    private static ToDo validToDo;

    @BeforeAll
    static void init(){
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");
        user = new User();
        user.setEmail("valid@cv.edu.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        user.setToDoSet(new HashSet<>());
        validToDo = new ToDo();
        validToDo.setOwner(user);
        validToDo.setTitle("Valid-Title");
        validToDo.setCreatedAt(LocalDateTime.now());
    }
    @Test
    void toDoWithValidTitle() {
        ToDo toDo = new ToDo();
        toDo.setTitle("Valid-Title");
        toDo.setOwner(user);
        toDo.setCreatedAt(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);

        assertEquals(0, violations.size());
    }

    @Test
    void createValidToDo(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validToDo);

        assertEquals(0, violations.size());
    }

//    @ParameterizedTest
//    @MethodSource("provideInvalidTitleToDo")
//    void constraintViolationInvalidTitle(String input, String errorValue) {
//        ToDo toDo = new ToDo();
//        toDo.setTitle(input);
//        toDo.setOwner(user);
//        toDo.setCreatedAt(LocalDateTime.now());
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);
//        assertEquals(1, violations.size());
//        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
//    }

    @Test
    void constraintViolationOnEmptyTitle() {
        ToDo emptyToDo = new ToDo();
        emptyToDo.setTitle("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(emptyToDo);
        assertEquals(1, violations.size());
    }

    @Test
    void constraintViolationOnNullTitle() {
        ToDo nullToDo = new ToDo();
        nullToDo.setTitle(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(nullToDo);
        assertEquals(1, violations.size());
    }
//
//    private static Stream<Arguments> provideInvalidTitleToDo(){
//        return Stream.of(
//                Arguments.of(56, 56),
//                Arguments.of(55,55),
//                Arguments.of(15, 15)
//        );
//    }
}
