package com.softserve.itacademy.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public class ToDo {
//TODO

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ToDo_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;

    @NotBlank(message = "The title cannot be empty")
    @Column(nullable = false, unique = true)
    private String title;

    private LocalDateTime created_at;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id")
    private User owner_id;

    public ToDo() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_at=" + created_at +
                ", owner_id=" + owner_id +
                '}';
    }
}
