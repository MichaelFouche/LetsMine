<%-- 
    Document   : tweetCollect
    Created on : 13 Aug 2016, 4:05:08 PM
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
        <jsp:useBean id="query_response" scope="request" type="String"/> 
        <jsp:useBean id="headingHTML" scope="request" type="String"/> 
        <jsp:useBean id="errors" scope="request" type="String"/> 
        <jsp:useBean id="success" scope="request" type="String"/> 
    </head>
    <body>
        <div class="container-fluid"> 
            <div class="page-header"> 
                <h1>LetsMine - Query</h1>
                ${headingHTML}
            </div> 
            <br><br><br>

            <form action="query_action.html" method="get" id="queryTextArea">
                <fieldset style='padding:5px 5px 10px 22px'>
                    <legend>Query Builder</legend>
                        <textarea name="query" form="queryTextArea" rows="4" cols="50">DATAMINE twitter HASHTAG rio2016 FROM Cape Town RADIUS 2000</textarea>
                        <br>
                    <input type="submit" value="Submit">
                </fieldset>
            </form>


            <div class="row" style='padding:5px 5px 10px 40px'> 
                   <font color="red">${errors}</font>
                   <font color="blue">${success}</font>
                   ${query_response}
            </div> 
        </div> 
    </body>
</html>
