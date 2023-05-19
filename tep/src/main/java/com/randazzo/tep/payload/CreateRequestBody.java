package com.randazzo.tep.payload;

import jakarta.validation.constraints.NotBlank;

public class CreateRequestBody {
    @NotBlank
    private String date;

    @NotBlank
    private String startHour;

    @NotBlank
    private String endHour;

    @NotBlank
    private String classe;

    @NotBlank
    private String section;


    public CreateRequestBody() {}

    public CreateRequestBody(String date, String startingHour, String endingHour, String classe, String section) {
        this.date = date;
        this.startHour = startingHour;
        this.endHour = endingHour;
        this.classe = classe;
        this.section = section;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
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


    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
