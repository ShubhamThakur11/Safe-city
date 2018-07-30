<?php
    $nm=$_GET['nm'];
    //$nm='jh';
    if(strlen($nm)>1)
    {
        $link=mysqli_connect('localhost','root','','android');
        $query="select mycomplaint from complaint where location like '%$nm%' ";
        $result=mysqli_query($link,$query);
        while($r=mysqli_fetch_row($result))
        {
            echo "<option>$r[0]</option>";
        }
    }


?>