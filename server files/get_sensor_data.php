<?php
    include('connection.php');
    
    $username = $_POST["patient_username"];
    
    
    
    $sql = "SELECT * FROM sensor_data WHERE username = '$username' ";
    
    $sql_result = mysqli_query($con, $sql);
    
    
     $jsonobject = array();
	 $response = array();
    
    // $response["success"] = 0;
    
    
    while($row = mysqli_fetch_array($sql_result)){
        
        
        $jsonobject["pulse"] = $row["pulse"];
        $jsonobject["body_temp"] = trim($row["body_temp"],"*F");
        $jsonobject["room_temp"] = trim($row["room_temp"],"*C");
        $jsonobject["humidity"] = trim($row["humidity"],"%");
        $jsonobject["air_quality"] = $row["air_quality"];
        $jsonobject["x_position"] = $row["x_position"];
        $jsonobject["y_position"] = $row["y_position"];
        $jsonobject["z_position"] = $row["z_position"];
        $jsonobject["username"] = $row["username"];
        $jsonobject["date"] = $row["date"];
        $jsonobject["time"] = $row["time"];
      
        
       
		
		$response[] = $jsonobject;
		
	//	echo '</br></br>';
		
		
    }
    
    
    
    echo json_encode($response);
    
?>