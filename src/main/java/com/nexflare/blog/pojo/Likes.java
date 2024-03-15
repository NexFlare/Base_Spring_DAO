package com.nexflare.blog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends AbstractDO{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID likeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "blog", referencedColumnName = "blogid")
    private Blog blog;

}
