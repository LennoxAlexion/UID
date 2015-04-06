<?php
 
/**
 * File to handle all API requests
 * Accepts GET and POST
 *
 * Each request will be identified by TAG
 * Response will be JSON data
 
  /**
 * check for POST request
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];
 
    // include db handler
    require_once 'include/DB_Functions.php';
    $db = new DB_Functions();
 
    // response Array
    $response = array("tag" => $tag, "error" => FALSE);
 
    // check for tag type
    if ($tag == 'login') {
        // Request type is check Login
        $username = $_POST['username'];
        $password = $_POST['password'];
 
        // check for user
        $user = $db->getUserByUsernameAndPassword($username, $password);
        if ($user != false) {
            // user found
            $response["error"] = FALSE;
            $response["uid"] = $user["Unique_Id"];
            $response["user"]["name"] = $user["Name"];
            $response["user"]["email"] = $user["Email"];
            $response["user"]["created_at"] = $user["Created_At"];
            $response["user"]["updated_at"] = $user["Updated_At"];
            $response["user"]["Role"] = $user["Role"];
            echo json_encode($response);
        } else {
            // user not found
            // echo json with error = 1
            $response["error"] = TRUE;
            $response["error_msg"] = "Incorrect username or password!";
            echo json_encode($response);
        }
    } else {
        // user failed to store
        $response["error"] = TRUE;
        $response["error_msg"] = "Unknow 'tag' value. It should be either 'login' or 'register'";
        echo json_encode($response);
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameter 'tag' is missing!";
    echo json_encode($response);
}
?>
