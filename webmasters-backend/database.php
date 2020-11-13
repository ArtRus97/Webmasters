<?php
   $con=mysqli_connect("t6ruar00@students.oamk.fi","t6ruar00","kameli123","opisk_t6ruar00");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

   $username = $_GET['username'];
   $password = $_GET['password'];
   $result = mysqli_query($con,"SELECT * FROM Products);
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   if($data){
      echo $data;
   }
   mysqli_close($con);
?>
