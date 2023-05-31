<?php

// Database configuration
$host = "localhost"; // Replace with your database host
$dbName = "id17963706_lab_info"; // Replace with your database name
$username = "id17963706_root"; // Replace with your database username
$password = "@LuckyMe11"; // Replace with your database password


// Create a PDO instance
try {
    $db = new PDO("mysql:host=$host;dbname=$dbName;charset=utf8", $username, $password);
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
