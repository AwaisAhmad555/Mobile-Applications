<?php

   include('connection.php');

   $name = $_POST['name'];
   $age = $_POST['age'];
   $latitude = $_POST['latitude'];
   $longitude = $_POST['longitude'];
   $environment = $_POST['environment'];
   $file_address = $_POST['file_address'];
   $file_address = "sample/(".time().")_$file_address";
   $location = "$latitude,$longitude";
   
   $date = date("Y/m/d");
   
   if($name != NULL && $age != NULL){
       
   $sql = "INSERT into sample_data(name,age,location,environment,file_address,time,date) values('$name','$age','$location','$environment','$file_address',ADDTIME(now(),'05:00:00'),'$date')";
   
   mysqli_query($con,$sql);
   }else {
       
       echo 'Name and age are NULL';
       
       
   }

 $file_path = "sample/";
 
 $file_path =  $file_path . "(" . time() . ")" .  "_" . basename($_FILES['uploaded_file']['name']);
 
 if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'],$file_path)){
     
    // $response['success'] = 1;
     
 }  else{
     
     
    // $response['success'] = 0;
     
     
 }

//echo json_encode($response);

?>