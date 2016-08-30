<%-- 
    Document   : reporting
    Created on : 17 Aug 2016, 4:46:27 PM
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
        <jsp:useBean id="loggedInUser" scope="request" type="String"/>
        <jsp:useBean id="users" scope="request" type="java.util.List"/>
        <jsp:useBean id="resultMessage" scope="request" type="String"/>
        <jsp:useBean id="comboReport" scope="request" type="java.util.List"/>
    </head>
    <body>
        <div class="container-fluid"> 
            <div class="page-header"> 
                <h1>LetsMine - Reporting</h1>
                ${headingHTML}
            </div> 
            <br>
            <br> <a href="tagCloud.html">Tag Cloud</a>
            
            <div class="row"> 
                 <c:forEach items="${users}" var="usersList">                              
                  <table class="table"> 
                     <thead> 
                     <tr> 
                         <th><h3><font color="blue">${usersList.getUsername()}</font></h3></th> 
                     </tr> 
                     <tr> 
                         <th>Initial Query</th> 
                         <th>Action</th> 
                     </tr> 
                     </thead> 
                     <tbody> 
                         <c:forEach items="${usersList.getQueries()}" var="userQuery"> 
                             <tr> 
                                 <td>${userQuery}</td> 
                                 
                                 <td>
                                     <form action="tagCloud.html" method="get" id="queryTextArea">
                                        <fieldset>
<!--                                            <select name="tagCloud">
                                                <c:forEach items="${comboReport}" var="id">
                                                    <option value="${id}">${id}</option>
                                                </c:forEach>
                                            </select>-->
                                            <input type="hidden" name="resultMessage" value="${userQuery}">
                                            <input type="submit" value="Display">
                                        </fieldset>
                                     </form>
                                 </td>  
                             </tr> 
                         </c:forEach> 
                     </tbody> 
                 </table> 
                 </c:forEach> 
                 
                 <h4>${resultMessage}</h4>
                 
                 
             </div> 
            
        </div> 
    </body>
</html>
