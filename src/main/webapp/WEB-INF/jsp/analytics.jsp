<%-- 
    Document   : analytics
    Created on : 17 Aug 2016, 4:45:59 PM
    Author     : michaelfouche
--%>
 <!DOCTYPE html> 
         <html lang="en"> 
         <head> 
             <title>MongoDB Tweets</title> 
             <meta name="viewport" content="width=device-width, initial-scale=1"> 
             <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" > 
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script> 
             <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script> 
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
        
        <jsp:useBean id="headingHTML" scope="request" type="String"/>
        <jsp:useBean id="loggedInUser" scope="request" type="String"/>
        <jsp:useBean id="users" scope="request" type="java.util.List"/>
        <jsp:useBean id="resultMessage" scope="request" type="String"/>
        <jsp:useBean id="comboAnalytics" scope="request" type="java.util.List"/>
    </head>
    <body>
        <div class="container-fluid"> 
            <div class="page-header"> 
                 <h1>Tag Cloud visualizer</h1> 
                 ${headingHTML}
            </div> 
            <div class="row"> 
                 <ul class="nav nav-tabs"> 
                     <li role="presentation" class="active"><a href="#">Collected TweetData for ${loggedInUser}</a></li> 
                 </ul> 
             </div> 
             <div class="row"> 
                 <c:forEach items="${users}" var="usersList">                              
                  <table class="table"> 
                     <thead> 
                     <tr> 
                         <th><h3><font color="blue">${usersList.getUsername()}</font></h3></th> 
                     </tr> 
                     <tr> 
                         <th>Queries</th> 
                         <th>Select Algorithm</th> 
                     </tr> 
                     </thead> 
                     <tbody> 
                         <c:forEach items="${usersList.getQueries()}" var="userQuery"> 
                             <tr> 
                                 <td>${userQuery}</td> 
                                 
                                 <td>
                                     <form action="analyticsRequest.html" method="get" id="queryTextArea">
                                        <fieldset>
                                            <select name="analyticsChoice">
                                                <c:forEach items="${comboAnalytics}" var="id">
                                                    <option value="${id}">${id}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="resultMessage" value="${userQuery}">
                                            <input type="submit" value="Convert for report">
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
