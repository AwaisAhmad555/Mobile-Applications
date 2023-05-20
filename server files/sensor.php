<?php
   
    include('connection.php');

    //$dbselect = mysqli_select_db($con, "id8992538_smart_health_care");
  	
	$pulse= $_GET['pulse'];
	$body_temp= $_GET['body_temp'];
	$room_temp= $_GET['room_temp'];
	$humidity=  $_GET['humidity'];
	$air_quality= $_GET['air_quality'];
	$x_position= $_GET['x_position'];
	$y_position= $_GET['y_position'];
	$z_position= $_GET['z_position'];
	
	$date = date("Y/m/d");
	
	$sql1 = "Select * from login_user ORDER BY id";
	
	$result1 = mysqli_query($con,$sql1);
	
	$last_id = mysqli_insert_id($con);
	
	while($row = mysqli_fetch_array($result1)){
	    
	    $username = $row['user'];
	    
	}
	
	
	$var1 = trim($body_temp,"*F");
	$pulse_num = (int)$pulse;
	$body_temp = (int)$var1;
	
   if ($pulse_num < 105 & $var1 > 94){

    
    $sql = "INSERT INTO id8992538_smart_health_care.sensor_data (pulse, body_temp, room_temp, humidity, air_quality, x_position, y_position, z_position,username,date,time) VALUES ('$pulse', '$body_temp', '$room_temp', '$humidity', '$air_quality', '$x_position', '$y_position', '$z_position' , '$username' ,'$date' , ADDTIME(now(),'05:00:00') )";    
 
    mysqli_query($con, $sql);
    
    echo '<br>';
    echo 'Record inserted Successfully ! ';
    echo '<br>';
    echo $pulse_num;
    echo '<br>';
    echo $var1;
    
} else {
    
    
    echo '<br>';
    echo 'Failed ! ';
    echo '<br>';
    echo $pulse_num;
    echo '<br>';
    echo $var1;
    
    
    
}

?>