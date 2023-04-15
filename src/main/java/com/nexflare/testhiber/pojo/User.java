package com.nexflare.testhiber.pojo;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nexflare.testhiber.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractDO{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="firstname")
    @NotBlank(message = "Required Field Missing")
    private String firstName;

    @Column(name="lastname")
    @NotBlank(message = "Required Field Missing")
    private String lastName;

    @Email
    @NotBlank(message = "Required Field Missing")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Required Field Missing")
    private String password;

    @Column(name="usertype")
    private UserType userType;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Blog> blogs;

}
