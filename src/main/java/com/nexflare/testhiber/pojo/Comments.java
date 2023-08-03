package com.nexflare.testhiber.pojo;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private String comment;
    //Foreign Key
    @ManyToOne
    @JoinColumn(name="blogid", referencedColumnName = "blogid")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

}
