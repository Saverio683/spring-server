package com.randazzo.tep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String startHour;

    @NotBlank
    private String endHour;

    @NotBlank
    private String date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EClass classe;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ESection sezione;

    @NotNull
    @Column(name = "approved_by_professore")
    @Enumerated(EnumType.STRING)
    private EStatus approvedByProfessore;

    @NotNull
    @Column(name = "approved_by_vicepreside")
    @Enumerated(EnumType.STRING)
    private EStatus approvedByVicepreside;

    public Request() {}

    public Request(Long id, String startHour, String endHour, String date, EClass classe, ESection sezione, EStatus approvedByProfessore, EStatus approvedByVicepreside) {
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.date = date;
        this.classe = classe;
        this.sezione = sezione;
        this.approvedByProfessore = approvedByProfessore;
        this.approvedByVicepreside = approvedByVicepreside;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EStatus getApprovedByProfessore() {
        return this.approvedByProfessore;
    }

    public void setApprovedByProfessore(EStatus approvedByProfessore) {
        this.approvedByProfessore = approvedByProfessore;
    }

    public EStatus getApprovedByVicepreside() {
        return this.approvedByVicepreside;
    }

    public void setApprovedByVicepreside(EStatus approvedByVicepreside) {
        this.approvedByVicepreside = approvedByVicepreside;
    }

    public EClass getClasse() {
        return this.classe;
    }

    public void setClasse(EClass classe) {
        this.classe = classe;
    }

    public ESection getSezione() {
        return this.sezione;
    }

    public void setSezione(ESection sezione) {
        this.sezione = sezione;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return this.endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

}
