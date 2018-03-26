package com.models;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int playerid;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String passHash;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "clan_id")
    private Clan clan;

    public Player() {
    }

    public Player(String username, String email, String passHash) {
        this.username = username;
        this.email = email;
        this.passHash = passHash;
    }


    public int getId() {
        return playerid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
