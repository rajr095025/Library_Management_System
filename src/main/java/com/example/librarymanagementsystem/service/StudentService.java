package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.RequestDTO.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDTO.UpdateStudentMobRequestDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.StudentResponseDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.UpdateStudentMobNoResponseDTO;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService{

    public String addStudent(StudentRequestDto studentRequestDto);

    public UpdateStudentMobNoResponseDTO updateMobNo(UpdateStudentMobRequestDTO updateStudentMobRequestDTO) throws StudentNotFoundException;

    public void deleteStudentById(Integer id) throws StudentNotFoundException;

    public StudentResponseDTO findStudentById(Integer id) throws StudentNotFoundException;

    public List<Student> findAllStudent();
}


