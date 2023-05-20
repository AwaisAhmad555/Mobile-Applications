<?php
    

        include('connection.php');
	
	$username = $_POST["username"];
	
	$query = "Select * from patient where username = '$username' ";
	
	$result = mysqli_query($con,$query);
	
	$response = array();
	
	$row = mysqli_fetch_array($result);
	
	$response["success"] = 0;
	
	if($result){
	
	$response["success"] = 1;
	$response["email"] = $row["email"];
	$response["ph_no"] = $row["ph_no"];
	$response["blood_group"] = $row["blood_group"];
	
	
	}
	
	echo json_encode($response);
	
	
	?>