<?php

// Database configuration
$servername = "localhost";
$username = "id20842745_username";
$password = "@EvsuMail11";
$dbname = "id20842745_root";


// Create a PDO instance
try {
    $db = new PDO("mysql:host=$servername;dbname=$dbname;charset=utf8", $username, $password);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}

// Fetch data from MySQL
$query = "SELECT * FROM java_users3"; // Replace with your table name
$stmt = $db->prepare($query);
$stmt->execute();
$data = $stmt->fetchAll(PDO::FETCH_ASSOC);

// Return data as JSON response
header('Content-Type: application/json');
echo json_encode($data);
