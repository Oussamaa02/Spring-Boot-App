package com.example.demo.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto create(@RequestBody SchoolDto dto){
        return schoolService.createSchool(dto);
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolService.findAll();
    }

    @DeleteMapping("/schools/{id}")
    public void delete(@PathVariable("id") Integer id){
        schoolService.delete(id);
    }

}
