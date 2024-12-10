package org.jsp.registration_form.repository;

import org.jsp.registration_form.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{

    boolean existsByEmail(String email);
    
}
