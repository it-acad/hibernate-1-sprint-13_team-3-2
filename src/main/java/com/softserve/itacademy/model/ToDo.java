package com.softserve.itacademy.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ToDo_sequence"),
                    @Parameter(name = "initial_value", value = "20"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;

    @NotBlank(message = "The title cannot be empty")
    @Column(nullable = false, unique = true)
    private String title;

    private LocalDateTime created_at;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(mappedBy = "toDoSet")
    private Set<User> users;

    @OneToMany(mappedBy = "todo")
    private Set<Task> tasks;

    public ToDo() {
        this.created_at = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public User getOwner() {
        return owner;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_at=" + created_at +
                ", owner_id=" + owner +
                '}';
    }
}
