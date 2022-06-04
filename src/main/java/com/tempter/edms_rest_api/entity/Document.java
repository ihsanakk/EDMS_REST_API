package com.tempter.edms_rest_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tempter.edms_rest_api.entity.enums.DocumentStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    @Column(length = 30, nullable = false)
    private String documentName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    private String currentDepartment;

    @Lob
    private byte[] document;

    @ManyToOne
    @JoinColumn(name = "publisher_user")
    private User publisherUser;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "document_signature", joinColumns = @JoinColumn(name = "document_id"),
    inverseJoinColumns = @JoinColumn(name = "signature_id"))
    private Set<Signature> signatureSet = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "document_signed_users", joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> signedUsers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "document_assigned_users", joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> assignedUsers = new HashSet<>();

}
