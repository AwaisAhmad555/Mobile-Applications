<?php


    $con = mysqli_connect("$SERVER/HOST_NAME", "$USER_NAME", "$PASSWORD", "$DATABASE_NAME");
    
	if($con){
		
		echo"connection Successfull!";
		
	}
	else{
		
		echo"connection Failed!";
		
	};
	
	?>