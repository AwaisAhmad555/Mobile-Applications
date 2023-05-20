<?php
    
    include('connection.php');

    $doctor_email = $_POST["doctor_email"];
    $employee_no = $_POST["employee_no"];
    
    
    $sql = "SELECT * FROM appointment WHERE emp_no = '$employee_no' AND doc_email = '$doctor_email' ";
    
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
        $jsonobject["request_date"] = date_format(date_create($row["request_date"]),"d/M/Y");
        
       
		
		$response[] = $jsonobject;
		
	//	echo '</br></br>';
		
		
    }
    
    
    
    echo json_encode($response);
    
?>