package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  {
    //TODO

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "20"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @NotBlank(message = "The first_name cannot be empty")
    @Column(nullable = false)
    @Pattern(regexp = "[A-Za-z\\d\\s-]")
    private String first_name;

    @NotBlank(message = "The last_name cannot be empty")
    @Column(nullable = false)
    @Pattern(regexp = "[A-Za-z\\d\\s-]")
    private String last_name;

    @Email(message = "The email cannot be empty", regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(name = "todo_collabolator",
            joinColumns = @JoinColumn(name = "collaborator_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id"))
    private Set<ToDo> toDoSet;

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Set<ToDo> getToDoSet() {
        return toDoSet;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToDoSet(Set<ToDo> toDoSet) {
        this.toDoSet = toDoSet;
    }
}
