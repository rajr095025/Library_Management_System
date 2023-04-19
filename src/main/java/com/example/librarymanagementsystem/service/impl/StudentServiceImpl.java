package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.DTO.RequestDTO.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDTO.UpdateStudentMobRequestDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.CardResponseDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.StudentResponseDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.UpdateStudentMobNoResponseDTO;
import com.example.librarymanagementsystem.entity.Card;
import com.example.librarymanagementsystem.entity.Student;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        Student student = new Student();
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo( studentRequestDto.getMobNo());

        //generate a new card for the student
        Card card = new Card();
        card.setStatus(CardStatus.ACTIVATED);
        card.setValidTill("2024-01-01");
        card.setStudent(student);


        //set card for the student
        student.setCard(card);
        studentRepository.save(student);
        return "Student added";
    }

    @Override
    public UpdateStudentMobNoResponseDTO updateMobNo(UpdateStudentMobRequestDTO updateStudentMobRequestDTO) throws StudentNotFoundException {

        Student student;
        Student updatedStudent;
        try{
            student = studentRepository.findById(updateStudentMobRequestDTO.getId()).get();
            student.setMobNo(updateStudentMobRequestDTO.getMobNo());
            updatedStudent = studentRepository.save(student);
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid student id");
        }
        UpdateStudentMobNoResponseDTO updateStudentMobNoResponseDTO = new UpdateStudentMobNoResponseDTO(updatedStudent.getName(),updatedStudent.getMobNo());
        return updateStudentMobNoResponseDTO;
    }

    @Override
    public void deleteStudentById(Integer id) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findById(id).get();
            studentRepository.delete(student);
        }
        catch (Exception e){
            throw new StudentNotFoundException("Student Not Found");
        }
    }

    @Override
    public StudentResponseDTO findStudentById(Integer id) throws StudentNotFoundException {
        try {
            Student student = studentRepository.findById(id).get();
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
            studentResponseDTO.setId(student.getId());
            studentResponseDTO.setName(student.getName());
            studentResponseDTO.setDepartment(student.getDepartment());
            studentResponseDTO.setAge(student.getAge());
            studentResponseDTO.setMobNo(student.getMobNo());

            CardResponseDTO cardResponseDTO = new CardResponseDTO();
            cardResponseDTO.setId(student.getCard().getId());
            cardResponseDTO.setIssueDate(student.getCard().getIssueDate());
            cardResponseDTO.setLastUpdatedDate(student.getCard().getLastUpdatedDate());
            cardResponseDTO.setStatus(student.getCard().getStatus());
            cardResponseDTO.setValidTill(student.getCard().getValidTill());

            studentResponseDTO.setCardResponseDTO(cardResponseDTO);

            return studentResponseDTO;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Student Not Found");
        }
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }


}
