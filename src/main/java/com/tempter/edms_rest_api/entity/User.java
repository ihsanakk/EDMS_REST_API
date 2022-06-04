package com.tempter.edms_rest_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tempter.edms_rest_api.entity.enums.ERole;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30, nullable = false)
    private String username;
    @Column(length = 30, nullable = false)
    private String email;
    @JsonIgnore
    @Column(length = 30, nullable = false)
    private String password;

    @ManyToMany(mappedBy = "signedUsers", fetch = FetchType.EAGER)
    private Set<Document> signedDocuments;
    @ManyToMany(mappedBy = "assignedUsers", fetch = FetchType.EAGER)
    private Set<Document> assignedDocuments;

    private String name;
    private String lastname;
    private String department;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
