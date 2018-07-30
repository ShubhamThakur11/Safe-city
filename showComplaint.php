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
            
            if(isset($_POST['bt']))
            {
                $link=mysqli_connect('localhost','root','','android');
                $loc=$_POST['loc'];
                $comp=$_POST['comp'];
                $query="select * from complaint where location='$loc' and mycomplaint='$comp';";
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
