package com.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Clan")
public class Clan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clanid;
    private String name;

    public Clan() {
    }

    public Clan(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "clan", cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, fetch = FetchType.EAGER)
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){
        if(this.players == null){
            this.players = new ArrayList<>();
        }
        this.players.add(player);
    }

    public int getId() {
        return clanid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
