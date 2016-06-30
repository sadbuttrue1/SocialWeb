package com.socialweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "Message.getMessagesOnLimit",
            query = "SELECT msg FROM Message msg JOIN msg.chat c WHERE c.Id = :chatId ORDER BY msg.messageDate ASC"),
    @NamedQuery(name = "Message.getLastMessages",
            query = "SELECT msg FROM Message msg JOIN msg.chat c WHERE c.Id = :chatId and msg.messageDate > :lastUpdate ORDER BY msg.messageDate ASC")
})

@Entity  
@Table(name="message")
public class Message {
    
    @Id
    @Column(name = "message_id")
    private int Id;
    
    @Column(name = "sender")
    private int sender;
    
    @ManyToOne
    @JoinColumn(name = "chat_id")    
    private Chat chat;
            
    @Column(name = "message_date")
    private long messageDate;
    
    @Column(name = "message_text")
    private String text;
    
    public Message(){}
    
    public Message(int sender, Chat chat, long date, String text){
        this.sender = sender;
        this.chat = chat;
        this.messageDate = date;
        this.text = text;
    }
    
    public int getId(){
        return Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public int getSender(){
        return sender;
    }
    
    public void setSender(int sender){
        this.sender = sender;
    }
    
    public Chat getChat(){
        return chat;
    }
    
    public void setChat(Chat chat){
        this.chat = chat;
    }
    
    public long getMessageDate(){
        return messageDate;
    }
    
    public void setMessageDate(long messageDate){
        this.messageDate = messageDate;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
}
