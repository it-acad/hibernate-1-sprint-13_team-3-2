package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "use")
public class User  {
    //TODO

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "20"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @NotBlank(message = "The first_name cannot be empty")
    @Column(nullable = false, unique = false)
    @Pattern(regexp = "[A-Za-z\\d\\s-]")
    private String first_name;

    @NotBlank(message = "The last_name cannot be empty")
    @Column(nullable = false, unique = false)
    @Pattern(regexp = "[A-Za-z\\d\\s-]")
    private String last_name;

    @Email(message = "The email cannot be empty", regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$")
    @Column(nullable = false, unique = false)
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @Column(nullable = false, unique = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "user")
    private List<ToDo> toDOList;
    public User() {
    }

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
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

    public List<ToDo> getToDOList() {
        return toDOList;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String second_name) {
        this.last_name = second_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToDOList(List<ToDo> toDOList) {
        this.toDOList = toDOList;
    }
}
