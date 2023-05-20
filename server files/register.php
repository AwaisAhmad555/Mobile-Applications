<?php
    
    include('connection.php');
	
	
    $name = $_POST["name_text"];
    $age = $_POST["age_edit"];
    $password = $_POST["password_edit"];
    $username = $_POST["username_text"];
    
    $response = array();
    //$response["success"] = true; 
    
    
    $sql = "SELECT * FROM login WHERE username = '$name'  or password = '$password' ";
    
    $sql_result = mysqli_query($con, $sql);
    
    if(mysqli_num_rows($sql_result) > 0){
        
        $response["success"] = "0";
        
    }
    
    else if($name != NULL && $username != NULL && $age != 0 && $password != "") {
    
    $statement = mysqli_prepare($con, "INSERT INTO login (name, age, password, username) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $age, $password, $username);
    mysqli_stmt_execute($statement);
    
    $query = "INSERT INTO patient (name, username, email, ph_no, blood_group) VALUES ('$name','$username' ,'NILL','NILL','NILL')";
    
    mysqli_query($con,$query);
    
    $response["success"] = "1"; 
    
    }
    

    //checking either any of value is null or not. If null value is found from android application request then json value
    // of '2' will be returned and application will show prompt 'please insert all values'

    if($name == NULL || $username == NULL || $age == 0 || $password == ""){
        
        $response["success"] = "2";
        
    }
    
    
    
    
    
    echo json_encode($response);
?>