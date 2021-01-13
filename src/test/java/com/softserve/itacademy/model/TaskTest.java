package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTest {
    private static Task validTask;
    private static ToDo toDo;
    private static State state;
    private static Role role;
    private static User user;

    @BeforeAll
    static void init(){
        role = new Role();
        role.setName("Valid-Name");
        user = new User();
        user.setEmail("valid@cv.edu.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(role);
        state = new State();
        state.setName("Valid-Name");
        toDo = new ToDo();
        toDo.setTitle("Valid-Title");
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setOwner(user);
        validTask = new Task();
        validTask.setName("Valid-Name");
        validTask.setState(state);
        validTask.setTodo(toDo);
        validTask.setPriority(Priority.MEDIUM);
    }
    @Test
    void createValidTask() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(validTask);

        assertEquals(0, violations.size());
    }
    @Test
    void taskWithValidName(){
        Task task = new Task();
        task.setName("Valid-Task");
        task.setState(state);
        task.setTodo(toDo);
        task.setPriority(Priority.MEDIUM);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(0, violations.size());
    }
    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void constraintViolationInvalidName(String input,String errorValue){
        Task task = new Task();
        task.setName(input);
        task.setPriority(validTask.getPriority());
        task.setTodo(validTask.getTodo());
        task.setState(validTask.getState());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }
    private static Stream<Arguments> provideInvalidName(){
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("", ""),
                Arguments.of("1", "1")
        );
    }
}
