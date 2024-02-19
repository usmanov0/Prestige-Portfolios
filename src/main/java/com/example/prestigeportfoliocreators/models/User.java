package com.example.prestigeportfoliocreators.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "liked_blogs")
    @ManyToMany(mappedBy = "likedUsers", fetch = FetchType.LAZY)
    private Set<Blog> likedBlogs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof User) || this.address == null) {
            return false;
        }
        User other = (User) o;
        return this.address.equals(other.address);
    }

    @Override
    public int hashCode() {
        if (address == null) {
            return 0;
        }
        return address.hashCode();
    }
}
