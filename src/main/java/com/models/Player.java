package com.models;


import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "player", fetch = FetchType.EAGER)
    private Set<Town> towns = new HashSet<>();

    public Player() {
    }

    public Player(String username, String email, String password) {
        if (username == null || email == null || password == null) {
            throw new IllegalArgumentException("Not all values are filled in");
        } else {

            this.username = username;
            if (!setEmail(email)) {
                throw new IllegalArgumentException("Email is invalid");
            }
            this.passHash = hashPassword(password);
        }

    }

    public Set<Town> getTowns() {
        if(towns == null){
            return new HashSet<Town>();
        }
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
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

    public boolean setEmail(String email) {
        if (validate(email)) {
            this.email = email;
            return true;
        }
        return false;
    }

    public void addTown(Town town) {
        this.towns.add(town);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.passHash);
    }

    public void setPassword(String password) {
        this.passHash = hashPassword(password);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(11);
        return BCrypt.hashpw(password, salt);
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
