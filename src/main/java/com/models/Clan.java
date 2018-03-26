package com.models;


import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "clan", fetch = FetchType.EAGER)
    private Set<Player> players = new HashSet<>();

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
        for (Player player : players) {
            player.setClan(this);
        }
    }

    public void addPlayer(Player player){
        player.setClan(this);
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
