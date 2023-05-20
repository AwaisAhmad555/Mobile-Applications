<?php

include('connection.php');


$doctor_name = $_POST["doctor_name"];
$doctor_username = $_POST["doctor_username"];
$doctor_email = $_POST["doctor_email"];
$doctor_type = $_POST["doctor_type"];
$Employee_number = $_POST["Employee_number"];
$doctor_Hospital = $_POST["doctor_Hospital"];
$doctor_PhoneNumber = $_POST["doctor_PhoneNumber"];
$doctor_city = $_POST["doctor_city"];
$doctor_password = $_POST["doctor_password"];


if($doctor_name == NULL && $doctor_username == NULL && $doctor_email == NULL && $doctor_type == NULL && $Employee_number == NULL && $doctor_Hospital == NULL && $doctor_PhoneNumber == NULL && $doctor_city == NULL && $doctor_password == NULL){
    
    $response["success"] = 0;
    
    
    }else{
    
    
    $checkquery = "Select * from Doctors where doctor_username = '$doctor_username' OR emp_no = '$Employee_number' OR email = '$doctor_email' OR ph_no = '$doctor_PhoneNumber' ";

    $sql_result = mysqli_query($con, $checkquery);
    
    
    if(mysqli_num_rows($sql_result) > 0){
    
    $response["success"] = 2;
    
    
}


$query = "INSERT INTO Doctors(doc_name,doctor_username,doc_type,emp_no,hospital,email,ph_no,city) values('$doctor_name','$doctor_username','$doctor_type','$Employee_number','$doctor_Hospital','$doctor_email','$doctor_PhoneNumber','$doctor_city')";

if(mysqli_query($con,$query)){


$new_query = "SELECT * FROM Doctors where doctor_username = '$doctor_username' and emp_no = '$Employee_number'";
    
    
$new_query_result = mysqli_query($con,$new_query);

while($row = mysqli_fetch_array($new_query_result)){
    
    $doc_id = $row["doc_id"];
    
}

$insert_query = "INSERT INTO doctor_login(doctor_username,doctor_password,doc_id) values('$doctor_username','$doctor_password','$doc_id')";

if(mysqli_query($con,$insert_query)){
    
    
    $response["success"] = 1;
    
    
   }
    
}

 
}


echo json_encode($response);



?>