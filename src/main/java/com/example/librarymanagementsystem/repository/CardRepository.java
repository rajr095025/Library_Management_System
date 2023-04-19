package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}
