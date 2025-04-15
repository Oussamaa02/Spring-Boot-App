package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentMapperTest {
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@mail.com",
                1
        );
        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());

    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //Given
        Student student = new Student(
                "Jane",
                "Smith",
                "jane@mail.com",
                20);

        //When
        StudentResponseDto dto = mapper.toStudentResponseDto(student);

        //Then
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
    }
}