package com.nexflare.testhiber.pojo;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class User extends AbstractDO{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name ="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    private String email;
    private String password;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Blog> blogs;


    public User(){}
}
