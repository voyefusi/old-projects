<?php
//require_once("support.php");

// IMPORTANT: This is just an example and you do not want to have plain passwords like this

$user = "main";
$password = "terps";

if(isset($_SERVER['PHP_AUTH_USER']) && isset($_SERVER['PHP_AUTH_PW']) &&
    $_SERVER['PHP_AUTH_USER'] == $user && $_SERVER['PHP_AUTH_PW'] == $password){
		echo"<h1> Administrative</h1>";
            
                echo"<p><strong>Select fields to display</strong><br />";
		echo"<select name='applicants[]' multiple='multiple'>";        
		echo"<option value='name'>name</option>";        
		echo"<option value='email'>email</option>";        
		echo"<option value='gpa'>gpa</option>";
	        echo"<option value='year'>year</option>";        
		echo"<option value='gender'>gender</option>";
                echo"</select></p>";        
                        
                echo"<p><strong>Select field to sort applications</strong>";
		echo"<select name='fields'>";
                echo"<option value='gpa'>gpa</option>";
		echo"<option value='name'>name</option>";
		echo"<option value='email'>email</option>";
		echo"<option value='year'>year</option>";
                echo"<option value='gender'>gender</option>";
                echo"</select></p>";  
                		
                echo"<p><strong>Name: </strong><input type='text' filter='filter' /><br /><br />";
                
                echo"<form action='displayapplications.php' method='post'>";
                echo"<input type='submit' value='Display Applications'/>";
                echo"</form></p>";
                
                echo"<form action='main.html' method='post'>";
                echo"<input type='submit' value='Return to main menu'/>";
                echo"</form></p>";
                
                
	} else {
		header("WWW-Authenticate: Basic realm=\"Example System\"");
		header("HTTP/1.0 401 Unauthorized");
		exit;
	}
?>