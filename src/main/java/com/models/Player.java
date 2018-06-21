package com.models;


import com.dbal.repository.TownRepository;
import com.dbal.specification.TownSpecification;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
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
    @Column(unique = true)
    private String email;
    @NotNull
    private String passHash;

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
        TownRepository townRepository = new TownRepository();
        List<Town> towns = townRepository.findAll(TownSpecification.getByPlayerId(this.playerid));

        if (towns == null) {
            return new HashSet<>();
        }
        return new HashSet<>(towns);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean setEmail(String email) {
        if (validate(email)) {
            email = email.toLowerCase();
            this.email = email;
            return true;
        }
        return false;
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
