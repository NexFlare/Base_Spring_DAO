package com.nexflare.testhiber.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nexflare.testhiber.enums.BlogStatus;
import com.nexflare.testhiber.enums.BlogType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog extends AbstractDO{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "blogid")
    private UUID blogId;

    private String title;

    @Column(length = 10000)
    private String text;

    @Nullable
    private String image;

    @Nullable
    private String location;

    @Nullable
    private BlogType type;

    @Column(name="blogstatus")
    private BlogStatus blogStatus;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    private List<Likes> likes;


}
