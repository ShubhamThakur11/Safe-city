<?php

	$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
			if(isset($_POST['username'])and isset($_POST['password']) and isset($_POST['email'])and isset($_POST['phone_no']))
			{
				$link=mysqli_connect("localhost","root","","android");
				$u=$_POST['username'];
				$p=$_POST['password'];
				$e=$_POST['email'];
				$ph=$_POST['phone_no'];
				$qr="select * from users where email='$e';";
				$rs=mysqli_query($link,$qr);
				$o=mysqli_num_rows($rs);
				if($o)	
				{	
					$response['error']=true;
					$response['message']="Already registered";
					$response['mail']=null;
				}
				else
				{
					$qry="insert into users values('$u','$p','$e','$ph');";
					$res=mysqli_query($link,$qry);
					$response['error']=false;
					$response['message']="User Registered";
					$response['mail']=null;
				}
				
			}
	}
	else
	{
		$response['error']=true;
		$response['message']="Invalid Request";
		$response['mail']=null;
	}
	
	echo json_encode($response);
?>