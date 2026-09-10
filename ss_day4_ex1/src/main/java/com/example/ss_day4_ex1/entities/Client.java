package com.example.ss_day4_ex1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter @Setter
public class Client {

    @Id
    private UUID id;

    @Column(name = "client_id")
    private String clientId;

    private String secret;

    @Column(name = "redirect_uri")
    private String redirectUri;

    @Column(name = "scope")
    private String scope;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Set<GrantType> grantTypes;
}
