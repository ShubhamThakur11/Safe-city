<?php

	$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
			if(isset($_POST['location'])and isset($_POST['mycomplaint']))
			{
				$link=mysqli_connect("localhost","root","","android");
				$l=$_POST['location'];
				$d=$_POST['mydate'];
				$m=$_POST['email'];
				$c=$_POST['mycomplaint'];
				$t=$_POST['mytime'];
				$lat=$_POST['mylatit'];
				$lon=$_POST['mylongit'];
				$qr="insert into complaint (location, mydate, mytime,mycomplaint,email,latitude,longitude) VALUES ('$l','$d','$t','$c','$m','$lat','$lon');";
				$rs=mysqli_query($link,$qr);
				if($rs)	
				{	
					$response['error']=false;
					$response['message']="Complaint registered";
					$q="select *from complaint where email='$m'";
					$r=mysqli_query($link,$q);
					$ans=mysqli_fetch_row($r);
					$response['id']=$ans[0];
					
				}
				else
				{
					$response['error']=true;
					$response['message']="Complaint Unsuccessful";
					$response['id']=null;
				}
				
			}
	}
	else
	{
		$response['error']=true;
		$response['message']="Invalid Request";
		$response['id']=null;
	}
	
	echo json_encode($response);
?>




