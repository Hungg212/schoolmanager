package com.example.schoolmanager.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "course_classes")
public class CourseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public CourseClass() {}

    public CourseClass(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
