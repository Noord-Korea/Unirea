package com.restserver.handler;

import com.restserver.json.result.account.*;

public interface IAccountHandler {
    void Login(Login data);
    void Logout(Logout data);

    void Register(Register data);
    void ChangePassword(ChangePassword data);

    void Update(UpdateAccount data);

    void HolidayReplacement(HolidayReplacement data);
    void Delete(Delete data);


}
