<?php


    include('connection.php');
	
	$response = array();
	
    $patient_name = $_POST["patient_name"];
    $patient_username = $_POST["patient_username"];
    $patient_email = $_POST["patient_email"];
    $doc_name = $_POST["doc_name"];
    $doc_type = $_POST["doc_type"];
    $emp_no = $_POST["emp_no"];
    $doc_email = $_POST["doc_email"];
    $ph_no = $_POST["ph_no"];
    $doc_address = $_POST["doc_address"];
    $appointment_date = date("0000-00-00");
    $from_time = "--";
    $to_time = "--";
    $rqst_dt = date("Y/m/d");
    $id = "1";
    
    
    
    //$statement = mysqli_prepare($con, "INSERT INTO appointment (patient, patient_username, patient_email, doctor_name, doc_type , emp_no, doc_email, ph_no, hospital, appointment_date, from_time, to_time, request_date ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    
   // mysqli_stmt_bind_param($statement, "sssssssssbssb", $patient_name, $patient_username, $patient_email, $doc_name, $doc_type, $emp_no, $doc_email, $ph_no, $doc_address, $appointment_date, $from_time, $to_time, $rqst_dt);
   // mysqli_stmt_execute($statement);
    
    //mysqli_stmt_store_result($statement);
    
    
    $query1 = "SELECT * FROM `appointment` where patient = '$patient_name' AND patient_username = '$patient_username' AND doctor_name = '$doc_name'  AND  emp_no = '$emp_no' AND  doc_email = '$doc_email' AND request_date = '$rqst_dt' ";
    
    $sql_result1 = mysqli_query($con,$query1);
    
    $row = mysqli_fetch_array($sql_result1);
    
    if($row > 1){
        
        
        $response["result"] = "2";
        
        
        
    }else{
    
    
    
    
    $query = "INSERT INTO `appointment` (`patient`, `patient_username`, `patient_email`, `doctor_name`, `doc_type`, `emp_no`, `doc_email`, `ph_no`, `hospital`, `appointment_date`, `from_time`, `to_time`, `request_date`) VALUES ('$patient_name', '$patient_username', '$patient_email', '$doc_name', '$doc_type', '$emp_no', '$doc_email', '$ph_no', '$doc_address', '$appointment_date', '$from_time', '$to_time', '$rqst_dt')";
    
    $sql_result = mysqli_query($con,$query);
    
    if($sql_result == 1){
        
        //echo 'query executed';
        $response["result"] = "1"; 
        
    }else {
        
        //echo 'failed';
        $response["result"] = "0";
        
    }
    
    }
    
    
    //$response["success"] = true; 
    
    
    echo json_encode($response);
?>