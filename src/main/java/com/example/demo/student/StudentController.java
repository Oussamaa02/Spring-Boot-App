package com.example.demo.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class StudentController {

    //Just for testing
    @GetMapping("/hello")
    public String sayHello(
            @RequestParam("user-name") String userName,
            @RequestParam("user-lastname") String userLastName
    ){
        return "Hello : " + userName + " " + userLastName;
    }

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto studentDto){
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents(){
        return studentService.findAllStudents();
    }

   

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentsByFirstName(@PathVariable("student-name") String fname){
        return studentService.findStudentsByFirstName(fname);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete (@PathVariable("id") Integer id){
        studentService.delete(id);
    }

    @GetMapping("/students/{school-id}")
    public List<StudentResponseDto> findStudentsBySchool (@PathVariable("school-id") Integer schoolId){
        return studentService.findStudentsBySchool(schoolId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodeArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        var errors = new HashMap<String , String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error ->{
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
