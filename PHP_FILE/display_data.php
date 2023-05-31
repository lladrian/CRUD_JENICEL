

 <?php
// Database connection credentials
$servername = "localhost";
$username = "id17963706_root";
$password = "@LuckyMe11";
$database = "id17963706_lab_info";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Retrieve data from the database
$sql = "SELECT * FROM java_users3";
$result = $conn->query($sql);

// Check if any rows were returned
if ($result->num_rows > 0) {
    // Create an array to hold the data
    $data = array();

    // Fetch rows and add them to the data array
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }

    // Return the data as JSON
    echo json_encode($data);
} else {
    // No rows found
    echo "No data found";
}

// Close the database connection
$conn->close();
?>