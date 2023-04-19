package com.example.librarymanagementsystem.DTO.ResponseDTO;

import com.example.librarymanagementsystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponseDTO {

    private int id;

    private String name;

    private int age;

    private Department department;

    private String mobNo;

    CardResponseDTO cardResponseDTO;

}
