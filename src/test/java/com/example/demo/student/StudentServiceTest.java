package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    //Which service we want to test
    @InjectMocks
    private  StudentService studentService;

    //Declare the dependencies
     @Mock
     private StudentRepository repository;
     @Mock
     private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        //Given
        StudentDto studentDto = new StudentDto(
                "Oussama",
                "Ammari",
                "oussama@gmail.com",
                1
        );
        Student student = new Student(
                "Oussama",
                "Ammari",
                "oussama@gmail.com",
                20
        );
        Student savedStudent = new Student(
                "Oussama",
                "Ammari",
                "oussama@gmail.com",
                20
        );
        savedStudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(studentDto))
                .thenReturn(student);
        when(repository.save(student))
                 .thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "John@mail.com"
                ));

        //When
        StudentResponseDto responseDto = studentService.saveStudent(studentDto);

        //Then
        assertEquals(studentDto.firstName(), student.getFirstName());
        assertEquals(studentDto.lastName(), student.getLastName());
        assertEquals(studentDto.email(), student.getEmail());

        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        //Given
        Student student = new Student(
                "Oussama",
                "Ammari",
                "oussama@mail.com",
                22
        );
        List<Student> students = new ArrayList<>();
        students.add(student);

        //Mock the calls
        when(repository.findAll())
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(student))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "John@mail.com"
                ));

        //When
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        //Then
        assertEquals(students.size(), responseDtos.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_find_student_by_id(){
        //Given
        Student student = new Student(
                "Oussama",
                "Ammari",
                "oussama@mail.com",
                22
        );
        Integer id = 1;

        //Mock the calls
        when(repository.findById(id))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(student))
                .thenReturn(new StudentResponseDto(
                        "Oussama",
                        "Ammari",
                        "oussama@mail.com"
                ));

        //When
        StudentResponseDto studentResponseDto = studentService.findStudentById(id);

        //Then
        assertEquals(studentResponseDto.firstName(), student.getFirstName());
        assertEquals(studentResponseDto.lastName(), student.getLastName());
        assertEquals(studentResponseDto.email(), student.getEmail());

        verify(repository, times(1)).findById(id);

    }

    @Test
    public void should_find_student_by_firstname(){
        //Given
        Student student = new Student(
                "Oussama",
                "Ammari",
                "oussama@mail.com",
                22
        );
        String fname = "Oussama";
        List<Student> students = new ArrayList<>();
        students.add(student);

        //Mock the calls
        when(repository.findAllByFirstNameContaining(fname))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(student))
                .thenReturn(new StudentResponseDto(
                        "Oussama",
                        "Ammari",
                        "oussama@mail.com"
                ));

        //When
        List<StudentResponseDto> studentResponseDtos = studentService.findStudentsByFirstName(fname);

        //Then
        assertEquals(studentResponseDtos.size(), students.size());

        verify(repository, times(1)).findAllByFirstNameContaining(fname);

    }


}