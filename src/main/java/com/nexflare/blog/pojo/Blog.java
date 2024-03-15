package com.nexflare.blog.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexflare.blog.enums.BlogStatus;
import com.nexflare.blog.enums.BlogType;
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

    @Column(length = 500)
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

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Likes> likes;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Comments> comments;

    @Override
    public int hashCode() {
        return this.blogId.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Blog blog = (Blog) obj;
        return this.blogId.equals(blog.getBlogId());
    }
}
