<?php
include_once 'include/Db_Connect.php';

function getRoles(){
    $db = new Db_Connect();
    // array for json response
    $response = array();
    $response["Role"] = array();
    
    // Mysql select query
    $result = mysql_query("SELECT DISTINCT Role FROM Users ORDER BY Role");
    
    while($row = mysql_fetch_array($result)){
        // temporary array to create single category
        $tmp = array();        
        // push category to final json array
        array_push($response["Role"], $tmp);
    }
    
    // keeping response header to json
    header('Content-Type: application/json');
    
    // echoing json result
    echo json_encode($response);
}

getCategories();
?>
