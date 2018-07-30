<html>
    <head>
    </head>
    <body >
        <table bgcolor="silver" align="center" width="80%" height=100% border=2 >
		<tr>
			<th align=center >ID</th>
			<th align=center >Date</th>
			<th align=center >Time</th>
			<th align=center >Location</th>
			<th align=center >Email</th>
			<th align=center >Complaint</th>
		</tr>
		<?php
    $m=$_GET['m'];
    //$nm='jh';
    if(strlen($m)>1)
    {
        $link=mysqli_connect('localhost','root','','android');
        $query="select * from complaint where email='$m' ";
        $result=mysqli_query($link,$query);
        while($r=mysqli_fetch_row($result))
        {
            echo "<tr><td align=center >$r[0]</td><td align=center >$r[2]</td><td align=center >$r[3]</td><td align=center >$r[1]</td><td
                    align=center >$r[5]</td><td align=center >$r[4]</td></tr>"; 
        }
    }


?>
 </table>
        
    </body>
</html>
