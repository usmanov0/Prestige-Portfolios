package com.example.prestigeportfoliocreators.models;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs", indexes = {@Index(name = "unix_time_ind", columnList = "unix_time"),
                                @Index(name = "title_ind", columnList = "title"),
                                @Index(name = "like_count_ind", columnList = "like_count")})
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Size(min = 3, max = 250, message = CustomErrors.BLOG_TITLE_ERROR)
    @NotBlank(message = CustomErrors.BLOG_TITLE_ERROR)
    private String title;

    @Column(name = "description")
    @Size(min = 3, max = 500, message = CustomErrors.BLOG_DESCRIPTION_ERROR)
    @NotBlank(message = CustomErrors.BLOG_DESCRIPTION_ERROR)
    private String description;

    @Column(name = "body")
    @Size(max = 5000, message = CustomErrors.BLOG_BODY_ERROR)
    @NotBlank(message = CustomErrors.BLOG_BODY_ERROR)
    private String body;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "date")
    private String date;

    @Column(name = "unix_time")
    private Long unixTime;

    @Getter(AccessLevel.NONE)
    @Column(name = "likes_users")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "blog_user",
            joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private final Set<User> likedUsers = new HashSet<>();

    @Transient
    private Boolean isLikedByUser;

    public void view(){
        this.viewCount++;
    }

    public void like(User user){
        user.getLikedBlogs().add(this);
        this.likedUsers.add(user);
        this.likeCount = likedUsers.size();
    }

    public void dislike(User user){
        user.getLikedBlogs().remove(this);
        this.likedUsers.remove(user);
        this.likeCount = likedUsers.size();
    }

    public void checksIsLikedByUser(User user){
        this.isLikedByUser = likedUsers.contains(user);
    }
}
