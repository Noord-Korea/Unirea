package com.restserver.handler;

import com.dbal.repository.IRepository;
import com.dbal.specification.PlayerSpecification;
import com.models.Player;
import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

public class AccountHandler implements IAccountHandler {
    private IRepository repository;

    public AccountHandler(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply Login(Login data) {
        Reply reply;
        Player player = (Player) repository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null){
            reply = new Reply(Status.NotFound, "Player doesn't exist");
        } else if (player.getEmail() != data.getEmail() || player.getPassword() != data.getPassword()){
            reply = new Reply(Status.NoAccess, "Your login credentials were incorrect");
        }
        return reply;

    }

    @Override
    public Reply Logout(Logout data) {
        return null;
    }

    @Override
    public Reply Register(Register data) {
        Reply reply;
        Player player = (Player) repository.findOne(PlayerSpecification.getByEmail(data.getEmail()));
        if (player == null){
            repository.save(data);
            reply = new Reply(Status.Ok, "Succesfully registered");
        } else{
            reply = new Reply(Status.NotFound, "Player doesn't exist");
        }
        return reply;
    }

    @Override
    public Reply ChangePassword(ChangePassword data) {
        return null;
    }

    @Override
    public Reply Update(UpdateAccount data) {
        return null;
    }

    @Override
    public Reply HolidayReplacement(HolidayReplacement data) {
        return null;
    }

    @Override
    public Reply Delete(Delete data) {
        return null;
    }
}
