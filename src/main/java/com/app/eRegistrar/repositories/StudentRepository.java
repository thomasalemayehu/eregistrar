package com.app.eRegistrar.repositories;

import com.app.eRegistrar.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // For search feature
    List<Student> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}