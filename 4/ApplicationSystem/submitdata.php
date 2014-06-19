 <?php
	//require_once("support.php");	

	$host = "localhost";
	$user = "dbuser";
	$password = "goodbyeWorld";
	$database = "applicationdb";
	$table = "applicants";
	$db = connectToDB($host, $user, $password, $database);
	
	/* someone to be added to table */
	$name = $_POST["name"];
        $email = $_POST["email"];
	$gender = $_POST["gender"];
	$GPA = $_POST["gpa"];
	$year = $_POST["year"];
        //$password = $_POST["password"];
        
	$sqlQuery = sprintf("insert into $table (name, email, gpa, year, gender, password) values ('%s', '%s', %s, %s)", 
				$name, $email, $gpa, $year, $gender, $password);
	$result = mysqli_query($db, $sqlQuery);
	if ($result) {
		$body = "<h3>The following entry has been added to the database</h3>";
	} else { 				   
		$body = "Inserting records failed.".mysqli_error($db);
	}
		
	/* Closing */
	mysqli_close($db);
	
	echo generatePage($body);

function connectToDB($host, $user, $password, $database) {
	$db = mysqli_connect($host, $user, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Connect failed.\n".mysqli_connect_error();
		exit();
	}
	return $db;
}
?>
