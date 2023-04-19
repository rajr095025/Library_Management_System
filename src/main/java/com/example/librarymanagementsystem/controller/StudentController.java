package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.RequestDTO.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDTO.UpdateStudentMobRequestDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.StudentResponseDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.UpdateStudentMobNoResponseDTO;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/update_mobile")
    public UpdateStudentMobNoResponseDTO updateMobNo(@RequestBody UpdateStudentMobRequestDTO updateStudentMobRequestDTO) throws StudentNotFoundException {
        UpdateStudentMobNoResponseDTO updateStudentMobNoResponseDTO;
        try {
            updateStudentMobNoResponseDTO = studentService.updateMobNo(updateStudentMobRequestDTO);
        }
        catch (StudentNotFoundException studentNotFoundException){
            throw studentNotFoundException;
        }
        return updateStudentMobNoResponseDTO;
    }

    // delete a student by id
    @DeleteMapping("/delete-by-id")
    public String deleteStudentById(@RequestParam("id") Integer id) throws StudentNotFoundException {
        studentService.deleteStudentById(id);
        return "Deleted Successfully";

    }

    // update the student by id

    // find a student by id
    @GetMapping("/find-by-id")
    public StudentResponseDTO findStudentById(@RequestParam("id") Integer id) throws StudentNotFoundException {
        return studentService.findStudentById(id);
    }

    // find all the students
    @GetMapping("/find-all")
    public List<Student> findAllStudent(){
        return studentService.findAllStudent();
    }

}

