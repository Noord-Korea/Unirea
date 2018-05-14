package com.restserver.handler;

import com.restserver.json.request.account.*;
import com.restserver.json.response.Reply;
import com.restserver.json.request.account.*;

public interface IAccountHandler {
    Reply login(Login data);
    Reply logout(Logout data);

    Reply register(Register data);
    Reply changePassword(ChangePassword data);

    Reply update(UpdateAccount data);

    Reply holidayReplacement(HolidayReplacement data);
    Reply delete(Delete data);


}
