<?php

	$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
			if(isset($_POST['password']) and isset($_POST['userid']))
			{
				$link=mysqli_connect("localhost","root","","android");
				$p=$_POST['password'];
				$u=$_POST['userid'];
				$qr="select * from admin where userid='$u' and password='$p';";
				$rs=mysqli_query($link,$qr);
				$o=mysqli_num_rows($rs);
				if($o==1)	
				{	
					$response['error']=false;
					$response['message']="Login Successfull";
					
				}
				else
				{
					$response['error']=true;
					$response['message']="Login Unsuccessfull";
					
				}
				
				
			}
	}
	else
	{
		$response['error']=true;
		$response['message']="Invalid Request";
		
	}
	
	echo json_encode($response);
?>