package com.models;

import javax.persistence.*;

@Entity
@Table(name="Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    private String passHash;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Player_Clan",
            joinColumns = {@JoinColumn(name = "player_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "clan_id",
                    referencedColumnName = "id")}
    )
    private Clan clan;

    public Player() {
    }

    public Player(String username, String email, String passHash) {
        this.username = username;
        this.email = email;
        this.passHash = passHash;
    }



    public int getId() {
        return id;
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
