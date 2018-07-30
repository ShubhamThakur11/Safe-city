<?php

	$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
			if(isset($_POST['password']) and isset($_POST['username']))
			{
				$link=mysqli_connect("localhost","root","","android");
				$p=$_POST['password'];
				$u=$_POST['username'];
				$qr="select * from users where username='$u' and password='$p';";
				$rs=mysqli_query($link,$qr);
				$ans=mysqli_fetch_row($rs);
				$o=mysqli_num_rows($rs);
				if($o>0)	
				{	
					$response['error']=false;
					$response['message']="Login Successfull";
					$response['mail']=$ans[2];
				}
				else
				{
					$response['error']=true;
					$response['message']="Login Unsuccessfull";
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