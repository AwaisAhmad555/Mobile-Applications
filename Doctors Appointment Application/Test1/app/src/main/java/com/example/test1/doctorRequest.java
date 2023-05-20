package com.example.test1;

import java.util.*;
import java.util.Map;
import java.util.HashMap;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class doctorRequest extends StringRequest {

    public static final url_class url_class = new url_class();

    //public String URL = url_class.url_function() + "doctor.php";

    public static final String DOC_Request = url_class.url_function() + "doctor.php";

    private Map <String,String> params;

    public  doctorRequest(Response.Listener<String> listener){

        super(Method.POST,DOC_Request,listener,null);

        params = new HashMap<>();



    }

}
