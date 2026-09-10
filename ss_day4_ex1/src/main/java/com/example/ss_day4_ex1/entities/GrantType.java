package com.example.ss_day4_ex1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "grant_types")
@Getter
@Setter
public class GrantType {

    @Id
    private UUID id;

    @Column(name = "grant_type")
    private String grantType;
}
