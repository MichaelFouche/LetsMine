 <!DOCTYPE html> 
         <html lang="en"> 
         <head> 
             <title>MongoDB Tweets</title> 
             <meta name="viewport" content="width=device-width, initial-scale=1"> 
             <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" > 
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script> 
             <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script> 
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
             <jsp:useBean id="myQueryList" scope="request" type="java.util.List"/>
             <jsp:useBean id="headingHTML" scope="request" type="String"/>
             <jsp:useBean id="loggedInUser" scope="request" type="String"/> 
         </head> 
         <body> 
         <div class="container-fluid"> 
             <div class="page-header"> 
                 <h1>LetsMine - MongoDB Tweets</h1>              
                ${headingHTML}
            </div> 
             <div class="row"> 
                 <ul class="nav nav-tabs"> 
                     <li role="presentation" class="active"><a href="#">Collected TweetData for ${loggedInUser}</a></li> 
                 </ul> 
             </div> 
             <div class="row"> 
                 <table class="table"> 
                     <thead> 
                     <tr> 
                         <th>User</th> 
                         <th>Date</th> 
                         <th>From</th> 
                         <th>Tweet</th> 
                     </tr> 
                     </thead> 
                     <tbody> 
                         <c:forEach items="${myQueryList}" var="request"> 
                             <tr> 
                                 <td>${request.getLetsMineUser()}</td> 
                                 <td>${request.getCreatedAt()}</td> 
                                 <td>${request.getFromUser()}</td>                                  
                                 <td>${request.getText()}</td> 
                             </tr> 
                         </c:forEach> 
                     </tbody> 
                 </table> 
             </div> 
         </div> 
         </body> 
         </html>