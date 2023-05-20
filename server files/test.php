<?php
   
    include('connection.php');

    //$dbselect = mysqli_select_db($con, "testing");
  	
	$value= $_GET['value'];
	//$value2 = $_GET['value2'];

    $sql = "INSERT INTO id8992538_smart_health_care.test (value) VALUES ('$value')";    
 
    mysqli_query($con, $sql);

?>