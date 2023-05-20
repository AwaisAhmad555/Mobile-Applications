<?php

include('connection.php');


$doctorUsername = $_POST["doctorUsername"];
$doctorPassword = $_POST["doctorPassword"];




if($doctorUsername == NULL || $doctorPassword == NULL ){
    
    $response["success"] = 0;
    
    
}else{
    
    
    $query = "SELECT * FROM doctor_login where doctor_username = '$doctorUsername' AND doctor_password = '$doctorPassword' ";
    
    
    $result = mysqli_query($con,$query);
    
    if(mysqli_num_rows($result) > 0){
        
        $response["success"] = 1;
        
    } else {
        
        $response["success"] = 2;
        
    }
    
}


echo json_encode($response);



?>