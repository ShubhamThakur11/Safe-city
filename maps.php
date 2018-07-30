<?php
require("phpsqlajax_dbinfo.php");

function parseToXML($htmlStr)
{
$xmlStr=str_replace('<','&lt;',$htmlStr);
$xmlStr=str_replace('>','&gt;',$xmlStr);
$xmlStr=str_replace('"','&quot;',$xmlStr);
$xmlStr=str_replace("'",'&#39;',$xmlStr);
$xmlStr=str_replace("&",'&amp;',$xmlStr);
return $xmlStr;
}

// Opens a connection to a MySQL server
$connection=mysqli_connect ('localhost', $username, $password);
if (!$connection) {
  die('Not connected : ' . mysqli_error());
}

// Set the active MySQL database
$db_selected = mysqli_select_db($connection,$database);
if (!$db_selected) {
  die ('Can\'t use db : ' . mysqli_error());
}

// Select all the rows in the markers table
$query = "SELECT * FROM complaint";
$result = mysqli_query($connection,$query);
if (!$result) {
  die('Invalid query: ' . mysqli_error());
}

header("Content-type: text/xml");

// Start XML file, echo parent node
echo "<?xml version='1.0' ?>";
echo '<markers>';
$ind=0;
// Iterate through the rows, printing XML nodes for each

/*while ($row = @mysqli_fetch_assoc($result)){
        $k=$row;
    }
  // Add to XML document node
  echo '<marker ';
  echo 'id="' . $k['complaintid'] . '" ';
  echo 'name="' . parseToXML($k['email']) . '" ';
 // echo 'address="' . parseToXML($k['address']) . '" ';
  echo 'lat="' . $k['latitude'] . '" ';
  echo 'lng="' . $k['longitude'] . '" ';
  //echo 'type="' . $k['type'] . '" ';
  echo '/>';
  $ind = $ind + 1;

*/



 

while ($row = @mysqli_fetch_assoc($result)){
  // Add to XML document node
  echo '<marker ';
  echo 'id="' . $row['complaintid'] . '" ';
  echo 'name="' . parseToXML($row['email']) . '" ';
  //echo 'address="' . parseToXML($row['address']) . '" ';
  echo 'lat="' . $row['latitude'] . '" ';
  echo 'lng="' . $row['longitude'] . '" ';
  //echo 'type="' . $row['type'] . '" ';
  echo '/>';
  $ind = $ind + 1;
}

// End XML file
echo '</markers>';

?>