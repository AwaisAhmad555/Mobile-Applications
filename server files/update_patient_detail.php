<?php

        include('connection.php');
	
	$username = $_POST["username"];
	$email = $_POST["email"];
	$ph_no = $_POST["ph_no"];
	$blood_group = $_POST["blood_group"];
	
	$query = "UPDATE patient set email = '$email', ph_no = '$ph_no', blood_group = '$blood_group' where username = '$username' ";
	
	$result = mysqli_query($con,$query);
	
	$response = array();
	
	
	$response["success"] = 0;
	
	if($result){
	
	$response["success"] = 1;
	
	}
	
	echo json_encode($response);
	
	
	?>