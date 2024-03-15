package com.nexflare.blog.pojo;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments extends AbstractDO{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 1000)
    private String comment;
    //Foreign Key
    @ManyToOne
    @JoinColumn(name="blogid", referencedColumnName = "blogid")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

}
