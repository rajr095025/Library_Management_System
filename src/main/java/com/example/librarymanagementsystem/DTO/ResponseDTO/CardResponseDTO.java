package com.example.librarymanagementsystem.DTO.ResponseDTO;

import com.example.librarymanagementsystem.enums.CardStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardResponseDTO {

    private int id;

    private Date issueDate;

    private Date lastUpdatedDate;

    private CardStatus status;

    private String validTill;

}
