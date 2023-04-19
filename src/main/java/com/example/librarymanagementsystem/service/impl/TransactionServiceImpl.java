package com.example.librarymanagementsystem.service.impl;


import com.example.librarymanagementsystem.DTO.RequestDTO.IssueBookRequestDTO;
import com.example.librarymanagementsystem.DTO.ResponseDTO.IssueBookResponseDTO;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Card;
import com.example.librarymanagementsystem.entity.Transaction;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.enums.TransactionStatus;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CardRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public IssueBookResponseDTO issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        Card card;
        try {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            card = cardRepository.findById(issueBookRequestDTO.getCardId()).get();

        } catch (Exception e) {
            throw new Exception("Not a Valid Card ID !! ");
        }

        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDTO.getBookId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Not a Valid Book ID !! ");
        }
        transaction.setBook(book);
        if (card.getStatus() != CardStatus.ACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not activated !! ");
        }
        if (book.isIssued()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available !! ");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        // book details
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);

        // card details
        card.getBooksIssued().add(book);
        card.getTransactionList().add(transaction);
        transactionRepository.save(transaction);

        // because card is a parent of both book and transaction, we can only save card, it will automatically save book and transaction.
        cardRepository.save(card);

        //prepare response DTO
        IssueBookResponseDTO issueBookResponseDTO = new IssueBookResponseDTO();
        issueBookResponseDTO.setBookName(book.getTitle());
        issueBookResponseDTO.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookResponseDTO.setTranscationNumber(transaction.getTransactionNumber());

        return issueBookResponseDTO;
    }
}
