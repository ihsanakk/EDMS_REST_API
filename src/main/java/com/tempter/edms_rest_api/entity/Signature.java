package com.tempter.edms_rest_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 60)
    private String signatureText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signedAt;

    @OneToOne
    @JoinColumn(name = "owner_user_id")
    private User signatureOwner;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "signed_document_id")
    private Document document;
}
