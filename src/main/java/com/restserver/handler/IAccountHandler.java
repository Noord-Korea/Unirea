package com.restserver.handler;

import com.restserver.json.request.account.*;
import com.restserver.json.response.Response;
import com.restserver.json.request.account.*;

public interface IAccountHandler {
    Response Login(Login data);
    void Logout(Logout data);

    void Register(Register data);
    void ChangePassword(ChangePassword data);

    void Update(UpdateAccount data);

    void HolidayReplacement(HolidayReplacement data);
    void Delete(Delete data);


}
