<?php
    
    include('connection.php');
    
    $patient_username = $_POST["patient_username"];
    $patientName = $_POST["patientName"];
    
    
    $sql = "SELECT * FROM appointment WHERE patient = '$patientName' AND patient_username = '$patient_username' ";
    
    $sql_result = mysqli_query($con, $sql);
    
    
     $jsonobject = array();
	 $response = array();
    
    // $response["success"] = 0;
    
    
    while($row = mysqli_fetch_array($sql_result)){
        
        
        $jsonobject["patient"] = $row["patient"];
        $jsonobject["patient_username"] = $row["patient_username"];
        $jsonobject["patient_email"] = $row["patient_email"];
        $jsonobject["doctor_name"] = $row["doctor_name"];
        $jsonobject["doc_type"] = $row["doc_type"];
        $jsonobject["emp_no"] = $row["emp_no"];
        $jsonobject["doc_email"] = $row["doc_email"];
        $jsonobject["ph_no"] = $row["ph_no"];
        $jsonobject["hospital"] = $row["hospital"];
        $jsonobject["appointment_date"] = $row["appointment_date"];
        $jsonobject["from_time"] = $row["from_time"];
        $jsonobject["to_time"] = $row["to_time"];
        $jsonobject["request_date"] = $row["request_date"];
        
       
		
		$response[] = $jsonobject;
		
	//	echo '</br></br>';
		
		
    }
    
    
    
    echo json_encode($response);
    
?>