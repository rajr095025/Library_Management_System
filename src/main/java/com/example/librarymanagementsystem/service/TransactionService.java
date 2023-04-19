package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.RequestDTO.IssueBookRequestDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.IssueBookResponseDTO;

public interface TransactionService {

    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO);
}
