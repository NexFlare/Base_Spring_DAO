package com.nexflare.testhiber.pojo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String comment;
    //Foreign Key
    @ManyToOne
    private Blog blogid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

}
