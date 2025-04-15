package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findAllByFirstNameContaining(String firstName);

    void deleteByFirstName(String firstName);

    List<Student> findAllBySchool_id(Integer schoolId);

}
