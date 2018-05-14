package com.restserver.handler;

import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;
import com.restserver.json.request.account.*;

public interface IAccountHandler {
    Reply Login(Login data);
    Reply Logout(Logout data);

    Reply Register(Register data);
    Reply ChangePassword(ChangePassword data);

    Reply Update(UpdateAccount data);

    Reply HolidayReplacement(HolidayReplacement data);
    Reply Delete(Delete data);


}
