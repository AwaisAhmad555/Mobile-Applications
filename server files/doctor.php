<?php
    include('connection.php');
    
    
    
    $sql = "SELECT * FROM Doctors";
    
    $sql_result = mysqli_query($con, $sql);
    
    
     $jsonobject = array();
	 $response = array();
    
    // $response["success"] = 0;
    
    
    while($row = mysqli_fetch_array($sql_result)){
        
        
        $jsonobject["doc_name"] = $row["doc_name"];
        $jsonobject["doc_type"] = $row["doc_type"];
        $jsonobject["emp_no"] = $row["emp_no"];
        $jsonobject["hospital"] = $row["hospital"];
		$jsonobject["email"] = $row["email"];
		$jsonobject["ph_no"] = $row["ph_no"];
		
		$response[] = $jsonobject;
		
	//	echo '</br></br>';
		
		
    }
    
    
    
    echo json_encode($response);
    
?>