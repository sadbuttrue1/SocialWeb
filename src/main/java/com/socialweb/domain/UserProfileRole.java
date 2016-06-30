package com.socialweb.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;


@Entity  
@Table(name="security_role") 
@NamedQueries({
    @NamedQuery(name = "UserProfileRole.findRoleByName",
            query = "SELECT upr FROM UserProfileRole upr WHERE upr.role = :role")
})
public class UserProfileRole implements Serializable {

    @Id  
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @NotBlank
    @Size(min = 2, max = 16)
    @Column(name = "user_role", unique = true)
    private String role;  
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<UserProfile> users = new HashSet<UserProfile>(0);
    
    public UserProfileRole(){
        
    }
    
    public UserProfileRole(String role){
        this.role = role;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getRole(){
        return this.role;
    } 
    
    public void setRole(String role){
        this.role = role;
    }
    
    public Set<UserProfile> getUsers(){
        return this.users;
    }
    
    public void setUsers(Set<UserProfile> users){
        this.users = users;
    }
}

