<?php
    include('connection.php');
    
   // $username = $_POST["username_text"];
    //$password = $_POST["password_edit"];
    
    //$statement = mysqli_prepare($con, "SELECT * FROM login WHERE username = ? AND password = ?");
    //mysqli_stmt_bind_param($statement, "ss", $username, $password);
    //mysqli_stmt_execute($statement);
    
   // mysqli_stmt_store_result($statement);
   // mysqli_stmt_bind_result($statement,$id , $name, $username, $age, $password);
    
    
   // $response = array();
    //$response["success"] = false; 
    //$response["success"] = 0;
    //$response["messege"] = "failed";
    
    //while(mysqli_stmt_fetch($statement)){
        //$response["success"] = true;
      //  $response["success"] = 1;
        //$response["messege"] = "success";
       // $response["name"] = $name;
        
       // $response["username"] = $username;
       // $response["age"] = $age;
       // $response["password"] = $password;
  //  }
    
    $username1 = $_POST["username_text"];
    $password1 = $_POST["password_edit"];
    
    $sql = "SELECT * FROM login WHERE username = '$username1'  and password = '$password1'  ";
    
    $sql_result = mysqli_query($con, $sql);
    
    
    $row = mysqli_fetch_array($sql_result);
    
    if($username1 == NULL && $password1 == NULL){   //if Required Fields data are NULL then response is 0
        
        $response["success"] = 2;
        
    }
    
    else
    {
        
        
        //else Portion 
        
        
    if($username1 == $row["username"] && $password1 == $row["password"])
    {
        
        //echo '1';
        //echo "id: " . $row["id"]. " - Name: " . $row["username"]. " -password: " . $row["password"]. "";
        
        $response["success"] = 1;
    
        $response["name"] = $row["name"];
        
        $response["username"] = $row["username"];
        $response["age"] = $row["age"];
        $response["password"] = $row["password"];
       
        
    } else if (mysqli_num_rows($sql_result) == NULL)
    
    {
        
       // echo '0';
       // echo "id: " . $row["id"]. " - Name: " . $row["username"]. " -password: " . $row["password"]. "";
       
       $response["success"] = 0;
        
    }
    
    
   // if(mysqli_num_rows($sql_result) === 1){
    
    //$row = mysqli_fetch_assoc($sql_result);
    
    
    
    
    
    //}
    }
    
    
    
    echo json_encode($response);
?>