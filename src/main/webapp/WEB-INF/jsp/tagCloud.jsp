<%-- 
    Document   : tagCloud
    Created on : 13 Aug 2016, 4:29:48 PM
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
        <jsp:useBean id="TagCloudHtml" scope="request" type="String"/> 
    </head>
    <body>
        <div class="container-fluid"> 
            <div class="page-header"> 
                 <h1>Tag Cloud visualizer</h1> 
                 <a href="/LetsMine">Home ||</a>
                 <a href="/LetsMine/myRequests.html">Show current database ||</a>
                 <a href="/LetsMine/tagCloud.html">Tag Cloud</a>
            </div> 
            <a href="/LetsMine">Home ||</a>
            <a href="/LetsMine/myRequests.html">Show current database ||</a>
            <a href="/LetsMine/tagCloud.html">Tag Cloud</a>
            <div class="row"> 
                <ul class="nav nav-tabs"> 
                    <li role="presentation" class="active"><a href="#">Tag Cloud</a></li> 
                </ul> 
            </div> 
            <div class="row"> 
               ${TagCloudHtml}
            </div> 

        </div> 
        
    </body>
</html>
