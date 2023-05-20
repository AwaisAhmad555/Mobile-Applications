<?php   



$con = mysqli_connect("localhost", "USERNAME", "PASSWORD", "DATABASE_NAME");
    
	if($con){
		
		 echo "connection Successfull!";
		
	}
	else{
		
		 echo "connection Failed!";
		
	};
	
	?>