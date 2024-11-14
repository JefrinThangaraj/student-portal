package com.ToDo.ToDo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String title;
    private String postedBy;
    private ZonedDateTime postedDate;
    private ZonedDateTime updatedDate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Task> tasks;

    // Automatically set dates when a new category is created
    @PrePersist
    protected void onCreate() {
        this.postedDate = ZonedDateTime.now();
        this.updatedDate = ZonedDateTime.now();
    }

    // Automatically update the 'updatedDate' field when the category is modified
    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = ZonedDateTime.now();
    }
}
