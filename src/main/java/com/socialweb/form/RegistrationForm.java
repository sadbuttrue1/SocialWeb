package com.socialweb.form;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegistrationForm {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4)
    private String password;

    @NotBlank
    @Size(min = 4)
    private String passwordConfirm;

    public RegistrationForm() {
    }

    public RegistrationForm(String email, String password, String passwordConfirm, String name, String surname, boolean displayEmail) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.name = name;
        this.surname = surname;
        this.displayEmail = displayEmail;
    }

    @NotBlank
    @Size(min = 2, max = 16)
    private String name;

    @NotBlank
    @Size(min = 2, max = 30)
    private String surname;

    private boolean displayEmail;

    public boolean isDisplayEmail() {
        return displayEmail;
    }

    public void setDisplayEmail(boolean displayEmail) {
        this.displayEmail = displayEmail;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
