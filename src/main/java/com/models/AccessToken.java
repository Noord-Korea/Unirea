package com.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "AccessToken")
public class AccessToken {

    @Id
    private String accessToken;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull
    private Date issued;
    @NotNull
    private Date expires;

    public AccessToken() {
    }

    public AccessToken(String accessToken, Player player, Date issued, Date expires) {
        this.accessToken = accessToken;
        this.player = player;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
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

    public void refresh(){
        long expirationTime = TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES);
        this.expires = new Date(System.currentTimeMillis() + expirationTime);
    }
}
