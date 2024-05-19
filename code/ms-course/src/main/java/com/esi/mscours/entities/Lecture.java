package com.esi.mscours.entities;

import com.esi.mscours.model.Debited;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Lecture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLecture;

    private Date date;
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroupe")
    private Groupe groupe;



    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "lecture_document",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documentList;

    @OneToOne
    @JoinColumn(name = "idConference")
    private Conference conference;

    @Transient
    private  List<Debited> payments;


    @Override
    public String toString() {
        return "Lecture{" +
                "idLecture=" + idLecture +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", conference=" + (conference != null ? conference.getIdConference() : "null") +
                '}';
    }
}