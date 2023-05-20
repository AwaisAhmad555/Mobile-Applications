<?php

$SERVER_NAME = "Place your Server/Host Name here (by default it is 'localHost') "
$USER_NAME = "Place your Server UserName Here"
$PASSWORD = "Place your Server Password Here"
$DATABASE_NAME = "Place your DataBase Name here"




$con = mysqli_connect("$SERVER_NAME", "$USER_NAME", "$PASSWORD", "$DATABASE_NAME");
    
	if($con){
		
		//echo"connection Successfull!";
		
	}
	else{
		
		//echo"connection Failed!";
		
	};


?>