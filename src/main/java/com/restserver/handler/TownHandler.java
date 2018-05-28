package com.restserver.handler;


import com.restserver.json.response.Reply;
import com.restserver.json.response.Status;

public class TownHandler implements ITownHandler {
    public Reply getTown(){
        return new Reply(Status.NotFound, "Not yet implemented");
    }
}
