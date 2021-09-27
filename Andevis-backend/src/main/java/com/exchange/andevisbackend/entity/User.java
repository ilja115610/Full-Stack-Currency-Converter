package com.exchange.andevisbackend.entity;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @SortNatural
    private SortedSet<ConversionRecord> recordsHistory = new TreeSet<>();


    public void addRecord (ConversionRecord record){

        if(record!=null){
            if(recordsHistory == null) {
                recordsHistory = new TreeSet<>();
            }
            recordsHistory.add(record);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public SortedSet<ConversionRecord> getRecordsHistory() {
        return recordsHistory;
    }

    public void setRecordsHistory(SortedSet<ConversionRecord> recordsHistory) {
        this.recordsHistory = recordsHistory;
    }
}
