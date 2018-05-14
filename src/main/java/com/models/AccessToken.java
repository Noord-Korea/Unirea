package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "AccessToken")
public class AccessToken {

    @Id
    private String accessToken;
    @NotNull
    private int expiresIn;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull
    private Date issued;
    @NotNull
    private Date expires;

    public AccessToken() {
    }

    public AccessToken(String accessToken, int expiresIn, Player player, Date issued, Date expires) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.player = player;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public Player getPlayer() {
        return player;
    }

    public Date getIssued() {
        return issued;
    }

    public Date getExpires() {
        return expires;
    }
}
