package com.socialweb.form;

public class SearchForm {

    private String name;

    private String surname;

    private String hometown;

    private String hobby;

    private String email;

    private Integer ageFrom;

    private Integer ageTo;

    private Integer pageNumber;

    public SearchForm() {
        this.pageNumber = 1;
    }
    
    
    
    public Integer getPageNumber() {
        return pageNumber == null ? 1 : pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getAgeTo() {
        return ageTo == null ? 0 : ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public Integer getAgeFrom() {
        return ageFrom == null ? 0 : ageFrom;
    }

    public void setAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
    }

    public String getName() {
        return name == null ? "" : name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname == null ? "" : surname.trim();
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHometown() {
        return hometown == null ? "" : hometown.trim();
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHobby() {
        return hobby == null ? "" : hobby.trim();
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEmail() {
        return email == null ? "" : email.trim();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
