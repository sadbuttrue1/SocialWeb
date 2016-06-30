package com.socialweb.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity  
@Table(name="user_profile")  
@NamedQueries({
    @NamedQuery(name = "UserProfile.findByUserName",
            query = "SELECT up FROM UserProfile up WHERE up.username = :username"),
   @NamedQuery(name = "UserProfile.findByUserId",
            query = "SELECT up FROM UserProfile up WHERE up.id = :id")  
})
public class UserProfile implements Serializable {  
      
    @Id  
    @GeneratedValue
    @Column(name = "id")
    private int id; //null 
    
    @NotBlank
    @Size(min = 2, max = 16)
    @Column(name = "username", unique = true)
    private String username;  
    
    @NotBlank
    @Size(min = 2, max = 16)
    @Column(name = "password")
    private String password; 
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", 
                joinColumns = { 
			@JoinColumn(name = "id_user", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_role", 
					nullable = false, updatable = false) })
    private Set<UserProfileRole> userRoles = new HashSet<UserProfileRole>(0);  

    public UserProfile() {
    }

    public UserProfile(String username, String password, Set<UserProfileRole> userRoles) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }
  
    
    
    public Integer getId() {  
        return id;  
    }  
  
    public void setId(Integer id) {  
        this.id = id;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public Set<UserProfileRole> getRoles() {  
        return userRoles;  
    }  
  
    public void setRoles(Set<UserProfileRole> userRoles) {  
        this.userRoles = userRoles;  
    }
}
 
