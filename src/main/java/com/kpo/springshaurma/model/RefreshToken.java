package com.kpo.springshaurma.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @Column(columnDefinition = "datetime")
    private LocalDateTime created;

    @ManyToOne
    ShaurmaUser shaurmaUser;
}
