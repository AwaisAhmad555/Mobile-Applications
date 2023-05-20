package com.example.test1;

import java.util.*;
import java.util.Map;
import java.util.HashMap;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


public class LoginRequest extends StringRequest {

    public static final url_class url_class =  new url_class();

    public static final String LOGIN_REQUEST_URL = url_class.url_function() + "login.php";

    private Map <String,String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){


        super(Method.POST,LOGIN_REQUEST_URL,listener,null);

        params = new HashMap<>();

        params.put("username_text", username);
        params.put("password_edit", password);
       // params.put();



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
