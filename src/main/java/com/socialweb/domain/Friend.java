/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialweb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexandr
 */
@NamedQueries({
    @NamedQuery(name = "Friend.Confirm",
            query = "SELECT ud FROM UserData ud WHERE ud.Id IN (SELECT f.userFriend FROM Friend AS f WHERE f.status = :statusWaiting AND f.user = :userId)"),
    @NamedQuery(name = "Friend.AllFriend",
            query = "SELECT ud FROM UserData ud WHERE ud.Id IN (SELECT f.user FROM Friend AS f WHERE f.status = :statusIsFriend AND f.userFriend = :userId) OR ud.Id IN (SELECT f.userFriend FROM Friend AS f WHERE f.status = :statusIsFriend AND f.user = :userId)"),
    @NamedQuery(name = "Friend.updateStatus",
            query = "UPDATE Friend f SET f.status = :newStatus WHERE f.user = :userId AND f.userFriend = :friendId"),
    @NamedQuery(name = "Friend.deleteByUserFriend",
            query = "DELETE FROM Friend f WHERE (f.user = :userId AND f.userFriend = :friendId) OR (f.user = :friendId AND f.userFriend = :userId)"),
    @NamedQuery(name = "Friend.status",            
            query = "SELECT f.status FROM Friend f WHERE (f.user = :userId AND f.userFriend = :friendId) OR (f.user = :friendId AND f.userFriend = :userId)")
})
@Entity
@Table(name = "friends")
public class Friend implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private int id;

    
    @Column(name = "user_id")
    private int user;

    
    @Column(name = "user_friend_id")
    private int userFriend;

    @Column(name = "friend_status")
    private int status;

    public Friend(int user, int userFriend, int status) {
        this.user = user;
        this.userFriend = userFriend;
        this.status = status;
    }

    public Friend() {
    }
    
    
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(int userFriend) {
        this.userFriend = userFriend;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
