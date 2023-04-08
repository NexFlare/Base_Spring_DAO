package com.nexflare.testhiber.pojo;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractDO{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
