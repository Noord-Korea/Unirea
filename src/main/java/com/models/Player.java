package com.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public Player() {
    }

    public Player(String username, String email, String passHash) {
        this.username = username;
        setEmail(email);
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
        if (validate(email)) {
            this.email = email;
        }
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
