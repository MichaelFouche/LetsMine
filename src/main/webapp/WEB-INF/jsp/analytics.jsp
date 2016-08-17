<%-- 
    Document   : analytics
    Created on : 17 Aug 2016, 4:45:59 PM
    Author     : michaelfouche
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LetsMine</title> 
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" > 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script> 
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script> 
        
        <jsp:useBean id="headingHTML" scope="request" type="String"/> 
    </head>
    <body>
        <div class="container-fluid"> 
            <div class="page-header"> 
                <h1>LetsMine - Analytics</h1>
                ${headingHTML}
            </div> 
            <br><br><br>
            
            
        </div> 
    </body>
</html>
