package com.example.test1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.*;
import java.util.Map;

public class RegisterRequest extends StringRequest {

public static final url_class url_class = new url_class();
private static final String REGISTER_REQUEST_URL = url_class.url_function() + "register.php";
private Map <String, String> params;

public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener){


   super(Method.POST, REGISTER_REQUEST_URL, listener, null);

   params = new HashMap<>();
   params.put("name_text" , name);
   params.put("username_text", username);
   params.put("password_edit", password);
   params.put("age_edit",age + "");

}

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
