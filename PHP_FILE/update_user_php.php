<?php



// Create a connection to the MySQL database
$servername = "localhost";
$username = "id17963706_root";
$password = "@LuckyMe11";
$dbname = "id17963706_lab_info";
$conn = new mysqli($servername, $username, $password, $dbname);

// Get the POST data
$fullname = $_POST['fullname'];
$email = $_POST['email'];
$password = $_POST['password'];
$id = $_POST['id'];
$idNumber =$_POST['idNumber'];
$phoneNumber =$_POST['phoneNumber'];

// Check the connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


if ($fullname==="" || $email==="" || $password==="" || $idNumber==="" || $phoneNumber==="" || $id==="") {
       echo "Fill in the blanks!";
 } else {
        $sql = "SELECT * FROM java_users3 WHERE email='$email'";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            echo "Email address already exist!";
        } else {
            if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                    echo "Invalid Email Address";
            } else {
                    $sql = "UPDATE java_users3 SET fullname='$fullname', studentID='$idNumber', phoneNumber='$phoneNumber', password='$password', email='$email' WHERE id='$id'";
                    if ($conn->query($sql) === TRUE) {
                        echo "Student data updated successfully!";
                    } else {
                        echo "Error: " . $sql . "<br>" . $conn->error;
                    }
            }
          
        }
 }
// Close the database connection
$conn->close();
?>