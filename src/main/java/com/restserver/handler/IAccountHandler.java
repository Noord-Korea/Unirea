package com.restserver.handler;

import com.restserver.json.result.account.ChangePassword;
import com.restserver.json.result.account.Login;
import com.restserver.json.result.account.Register;

public interface IAccountHandler {
    void Login(Login data);
    void Logout();

    void Register(Register data);
    void ChangePassword(ChangePassword data);

    void Update();

    void HolidayReplacement();
    void Delete();


}
