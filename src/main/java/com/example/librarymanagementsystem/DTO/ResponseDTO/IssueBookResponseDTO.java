package com.example.librarymanagementsystem.DTO.ResponseDTO;


import com.example.librarymanagementsystem.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookResponseDTO {

    private String transcationNumber;

    private TransactionStatus transactionStatus;

    private String bookName;

}
