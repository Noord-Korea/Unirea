package com.restserver.handler;

import com.restserver.json.result.account.Login;
import com.restserver.json.result.account.Register;

public interface IAccountHandler {
    void Login(Login data);
    void Register(Register data);
}
