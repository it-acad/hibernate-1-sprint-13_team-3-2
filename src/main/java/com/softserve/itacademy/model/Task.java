package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "state_sequence"),
                    @Parameter(name = "initial_value", value = "20"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @NotNull
    @Size(min = 3, max = 200, message = "Name must be with minimum 3 and maximum 200 any symbols")
    private String name;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "todo_id")
    private ToDo todo;

    public Task() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return "RTask {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", priority = '" + priority + '\'' +
                ", state = '" + state + '\'' +
                ", todo = '" + todo + '\'' +
                "} ";
    }
}
