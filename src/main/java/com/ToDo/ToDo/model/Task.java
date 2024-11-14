package com.ToDo.ToDo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.HIGH;

    private ZonedDateTime deadlineDate;
    private ZonedDateTime deadlineTime;
    private ZonedDateTime postedDate;
    private ZonedDateTime updatedDate;
    private String postedBy;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Setter
    @Column(name = "user_id")
    private String userId;

    @PrePersist
    protected void onCreate() {
        postedDate = ZonedDateTime.now();
        updatedDate = ZonedDateTime.now();
    }

    @Getter
    @Transient
    private String categoryId;

}
