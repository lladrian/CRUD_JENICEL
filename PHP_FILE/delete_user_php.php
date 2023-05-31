<?php
// Get the POST data
$id = $_POST['id'];

// Create a connection to the MySQL database
$servername = "localhost";
$username = "id20842745_username";
$password = "@EvsuMail11";
$dbname = "id20842745_root";

$conn = new mysqli($servername, $username, $password, $dbname);

// Check the connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Prepare and execute the SQL query
$sql = "DELETE FROM java_users3 WHERE id='$id'";
if ($conn->query($sql) === TRUE) {
    echo "User deleted successfully!";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

// Close the database connection
$conn->close();
?>
