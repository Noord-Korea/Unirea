package com.restserver;

import com.google.gson.Gson;
import com.restserver.json.request.account.Login;
import com.restserver.json.request.account.Register;
import com.restserver.json.request.town.BaseTownRequest;
import com.restserver.json.request.town.TownId;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class TestConnection {

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        Register register = new Register("Henk@gmail.com", "Henk123", "Henk");
        String payload = gson.toJson(register);
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8090/account/register");
        request.setEntity(entity);

        Login login = new Login("Henk@gmail.com", "Henk123");
        String payloadLogin = gson.toJson(login);
        StringEntity entityLogin = new StringEntity(payloadLogin,
                ContentType.APPLICATION_JSON);

        HttpClient httpClientLogin = HttpClientBuilder.create().build();
        HttpPost requestLogin = new HttpPost("http://localhost:8090/account/login");
        requestLogin.setEntity(entityLogin);

        //HttpResponse response = httpClient.execute(request);

        HttpResponse responseLogin = httpClientLogin.execute(requestLogin);
        String accessToken = "";
        try {
            BufferedReader rd;
            InputStreamReader stream = new InputStreamReader(responseLogin.getEntity().getContent());
            rd = new BufferedReader(stream);

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            stream.close();
            System.out.println("Json: " + result.toString());
            accessToken = result.toString();
        } catch (IOException e){
        }

        BaseTownRequest createTownId = new BaseTownRequest(accessToken);
        String payloadcreateTown = gson.toJson(createTownId);
        StringEntity entitycreateTown = new StringEntity(payloadcreateTown,
                ContentType.APPLICATION_JSON);

        HttpClient httpClientcreateTown = HttpClientBuilder.create().build();
        HttpPost requestcreateTown = new HttpPost("http://localhost:8090/town/create");
        requestcreateTown.setEntity(entitycreateTown);

        HttpResponse responsecreateTown = httpClientcreateTown.execute(requestcreateTown);

        //

        TownId townId = new TownId(accessToken, 1);
        String payloadTown = gson.toJson(townId);
        StringEntity entityTown = new StringEntity(payloadTown,
                ContentType.APPLICATION_JSON);

        HttpClient httpClientTown = HttpClientBuilder.create().build();
        HttpPost requestTown = new HttpPost("http://localhost:8090/town/get");
        requestTown.setEntity(entityTown);

        HttpResponse responseTown = httpClientTown.execute(requestTown);
        try {
            BufferedReader rd;
            InputStreamReader stream = new InputStreamReader(responseTown.getEntity().getContent());
            rd = new BufferedReader(stream);

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            stream.close();
            System.out.println("Json: " + result.toString());
        } catch (IOException e){
        }
    }
}
