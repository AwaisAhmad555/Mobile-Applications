<?php   

//This php script is for adding current login user into current login table so health values of only curent login user 
//can be monitored on mobile app


include('connection.php');	
	
    


$user = $_POST["user"];
    
    


$delete_query = "DELETE from login_user";
	

	

mysqli_query($con,$delete_query);
  
  
    
    

$query = "INSERT INTO login_user(user) values('$user')";
   
 
    

$result = mysqli_query($con,$query);
    
    


$response = array();
    

    

if($result == 1){
        
        
        

$response["success"] = "1";
        
        
    

}


else {
        
        
       

 $response["success"] = "0";
        
        
    

}
    
    
    

json_encode($response);
    
    


?>