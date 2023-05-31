<?php

    $servername = "localhost";
    $username = "id17963706_root";
    $password = "@LuckyMe11";
    
    try {
      $conn = new PDO("mysql:host=$servername;dbname=id17963706_lab_info", $username, $password);
      // set the PDO error mode to exception
      $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      //echo "Connected successfully";
    } catch(PDOException $e) {
      echo "Connection failed: " . $e->getMessage();
    }

$email = $_POST['email'];
$password = $_POST['password'];


 if (!(empty($email)) && !(empty($password)) ) {
     //$password = md5($_POST['password']);
    $password = $_POST['password'];


    $sql = "SELECT * FROM java_users3 WHERE email= '$email' AND password = '$password'";
    $stmt = $conn->query($sql);
    if ($stmt->rowCount() > 0) {
        echo "Login successfully!";
    } else {
        echo "Wrong username or password!";
    }
} else {
        echo "Fill in the blanks!";
}

?>
