package com.nexflare.testhiber.pojo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name ="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    private String email;


    public User(){}
}
