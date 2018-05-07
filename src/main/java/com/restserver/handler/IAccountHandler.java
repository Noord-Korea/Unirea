package com.restserver.handler;

import com.restserver.json.result.Login;
import com.restserver.json.result.Register;

public interface IAccountHandler {
    void Login(Login data);
    void Register(Register data);
    void ChangePassword();
}
