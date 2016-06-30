package com.socialweb.domain;

import com.socialweb.utility.Help;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@NamedQueries({
    @NamedQuery(name = "UserData.findById",
            query = "SELECT ud FROM UserData ud WHERE ud.Id = :user_id"),
    @NamedQuery(name = "UserData.findUsers",
            query = "SELECT distinct ud FROM UserData ud "
            + "LEFT JOIN ud.emails em "
            + "WHERE ud.name LIKE CONCAT('%', :name, '%') "
            + "AND ud.surname LIKE CONCAT('%', :surname, '%') "
            + "AND ud.hometown LIKE CONCAT('%', :hometown, '%') "
            + "AND ud.birth BETWEEN :dateFrom AND :dateTo "
            + "AND (:email = '' OR :email = em.email)"),
//    @NamedQuery(name = "UserData.updateAll",
//            query = "UPDATE UserData ud SET ud.name = :name, ud.surname = :surname,"
//                    + " ud.hobby = :hobby, ud.birth = :birth, ud.email = :email,"
//                    + " ud.phone = :phone WHERE ud.Id = :id")
    @NamedQuery(name = "UserData.findByProfileLink",
            query = "SELECT ud FROM UserData ud JOIN FETCH ud.emails e WHERE ud.profileLink = :profileLink"),
    @NamedQuery(name = "UserData.findByIdEager",
            query = "SELECT ud FROM UserData ud JOIN FETCH ud.emails e WHERE ud.Id = :user_id")
})

@Entity
@Table(name = "user_data")
public class UserData implements Serializable {

    @Id
    @Column(name = "user_id")
    private int Id;

    @NotBlank
    @Size(min = 2, max = 16)
    @Column(name = "user_name")
    private String name;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "user_surname")
    private String surname;

    @Past
    @Column(name = "user_birth")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @Column(name = "user_hobby")
    private String hobby;

    @Column(name = "user_phone")
    private String phone;

    @OneToMany(mappedBy = "userData", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<EmailUserData> emails = new ArrayList<>();

    @Column(name = "user_photo")
    private String photo;

    @Transient
    private MultipartFile photoImg;  

    public MultipartFile getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(MultipartFile photoImg) {
        this.photoImg = photoImg;
    }

    public List<EmailUserData> getEmails() {
        return emails;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setEmails(List<EmailUserData> emails) {
        this.emails = emails;
    }

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "profile_link", unique = true)
    private String profileLink;

    public UserData() {
    }

    
    
    public UserData(int Id, String name, String surname, String profileLink) {
        this.Id = Id;
        this.name = name;
        this.surname = surname;
        this.profileLink = profileLink;
    }

    public String getProfileLink() {
        return Help.isReally(profileLink) ? profileLink : ((Integer) Id).toString();
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String parseDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (birth != null) {
            return dateFormat.format(birth);
        }
        return "";
    }

    public String displayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (birth != null) {
            return dateFormat.format(birth);
        }
        return "";
    }

}
