package com.nexflare.blog.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexflare.blog.enums.UserType;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email")
})
public class User extends AbstractDO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="firstname")
    @NotBlank(message = "Required Field Missing")
    private String firstName;

    @Column(name="lastname")
    @NotBlank(message = "Required Field Missing")
    private String lastName;

    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Required Field Missing")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Required Field Missing")
    private String password;

    @Column(name="usertype")
    private UserType userType;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Blog> blogs;

}
