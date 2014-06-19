<?php $scriptName = $_SERVER["PHP_SELF"];
	if(!isset($_POST["password"])){
        echo "<form action='$scriptName' method='post'>";
			echo"<p><strong>Name: </strong><input type='text' name='name' /><br /><br />";
		echo"<strong>Email: </strong><input type='text' name='email' /><br /><br />";
                echo"<strong>GPA: </strong><input type='text' name='gpa' /><br /><br /></p>";            
             
            echo"<p><strong>Year:</strong><br />";
		echo"10<input type='radio' name='year' value='10' />&nbsp;";
		echo"11<input type='radio' name='year' value='11' />";
                echo"12<input type='radio' name='year' value='12' /></p>";
              
             echo"<p><strong>Gender:</strong><br />";
		 echo"M<input type='radio' name='gender' value='male' />&nbsp;";
		 echo"F<input type='radio' name='gender' value='female' /></p>";
                    
			
			echo "<p><strong>Password:</strong><input type='password' name='password' /></p>";       
                        echo "<p><strong>Verify Password:</strong><input type='password' name='password2' /></p>";
	                echo"<input type='submit' value='Submit Data'/>";
		        echo"</form></p>";
                
                echo"<form action='main.html' method='post'>";
                echo"<input type='submit' value='Return to main menu'/>";
                echo"</form></p>";
           
        }
        else {
            if ($_POST['password2'] === $_POST['password']){
                include ("submitdata.php");   
            }
            else {
        	echo "<form action='submitapplication.php' method='post'>";
			
			echo"<p><strong>Name: </strong><input type='text' name='name' /><br /><br />";
		echo"<strong>Email: </strong><input type='text' name='email' /><br /><br />";
                echo"<strong>GPA: </strong><input type='text' name='gpa' /><br /><br /></p>";            
             
            echo"<p><strong>Year:</strong><br />";
		echo"10<input type='radio' name='year' value='10' />&nbsp;";
		echo"11<input type='radio' name='year' value='11' />";
                echo"12<input type='radio' name='year' value='12' /></p>";
              
             echo"<p><strong>Gender:</strong><br />";
		 echo"M<input type='radio' name='gender' value='male' />&nbsp;";
		 echo"F<input type='radio' name='gender' value='female' /></p>";
                    
			
			echo "<p><strong>Password:</strong><input type='password' name='password' /></p>";       
                        echo "<p><strong>Verify Password:</strong><input type='password' name='password2' /></p>";
                        
            echo"<p><form action='submitapplication.php' method='post'>";
                 echo"<input type='submit' value='Submit Data'/>";
                echo"</form></p>";
                
                echo"<form action='main.html' method='post'>";
                echo"<input type='submit' value='Return to main menu'/>";
                echo"</form></p>";
		echo"password entries do not match";
            }
        }
?>