package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.RequestDTO.IssueBookRequestDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.IssueBookResponseDTO;
import com.example.librarymanagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public IssueBookResponseDTO issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO){
        return transactionService.issueBook(issueBookRequestDTO);
    }

    // return book
}
