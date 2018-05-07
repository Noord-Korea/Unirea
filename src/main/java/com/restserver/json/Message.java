package com.restserver.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.restserver.json.result.account.Result;

public class Message {
    private String action;
    private String rawData;
    private Result data;

    public Message(String action) {
        this.action = action;
    }

    public Message(String action, String rawData) {
        this.action = action;
        this.rawData = rawData;
    }

    public Message(String action, Result data) {
        this.action = action;
        Gson gson = new Gson();
        this.rawData = gson.toJson(data);
    }

    public String getAction() {
        return action;
    }

    public String getRawData() {
        return rawData;
    }

    public Result getData() {
        return data;
    }

    public void parseData(Class<? extends Result> iAction){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(rawData).getAsJsonObject();
        Gson g = new Gson();
        this.data = g.fromJson(jsonObject, iAction);
    }
}
