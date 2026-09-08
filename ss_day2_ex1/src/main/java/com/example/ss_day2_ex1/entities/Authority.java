package com.example.ss_day2_ex1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority {

    @Id
    private UUID id;

    private String name;
}
