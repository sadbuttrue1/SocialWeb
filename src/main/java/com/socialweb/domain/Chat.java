package com.socialweb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@NamedQueries({
    @NamedQuery(name = "Chat.getAllChats",
            query = "SELECT ch FROM Chat ch WHERE ch.firstPerson = :user_id or ch.secondPerson = :user_id"),
    @NamedQuery(name = "Chat.getChat",
            query = "SELECT ch FROM Chat ch WHERE (ch.firstPerson = :sender and ch.secondPerson = :receiver) "
                    + "or (ch.firstPerson = :receiver and ch.secondPerson = :sender)")    
})

@Entity  
@Table(name="chat")
public class Chat implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private int Id;
    
    @Column(name = "first_person")
    private int firstPerson;
    
    @Column(name = "second_person")
    private int secondPerson;
    
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
    
    public Chat(){}
    
    public Chat(int firstPerson, int secondPerson){
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
    }
    
    
    public int getId(){
        return Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public int getFirstPerson(){
        return firstPerson;
    }
    
    public void setFirstPerson(int firstPerson) {
        this.firstPerson = firstPerson;
    }

    public int getSecondPerson(){
        return secondPerson;
    }
    
    public void setSecondPerson(int secondPerson) {
        this.secondPerson = secondPerson;
    }
    
    public List<Message> getMessages() {
        return messages;
    }
    
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }    
}
