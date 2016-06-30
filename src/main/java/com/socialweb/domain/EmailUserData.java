package com.socialweb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author alexandr
 */
@Entity
@Table(name = "email")
@NamedQueries({
    @NamedQuery(name = "Email.deleteId",
            query = "DELETE FROM EmailUserData eud WHERE eud.Id = :id")
})
public class EmailUserData implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "email_id")
    private int Id;

    @ManyToOne
    @JoinColumn(name = "user_id")    
    private UserData userData;

    @Email
    @Column(name = "email")
    private String email;
    
    @Transient
    private boolean remove;

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public EmailUserData() {
    }

    public EmailUserData(UserData userData, String email) {
        this.userData = userData;
        this.email = email;
    }

    public EmailUserData(int Id, UserData userData, String email, boolean remove) {
        this.Id = Id;
        this.userData = userData;
        this.email = email;
        this.remove = remove;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

}
