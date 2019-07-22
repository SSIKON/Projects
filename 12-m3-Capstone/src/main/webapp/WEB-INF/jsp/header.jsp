<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<c:url var="cssUrl" value="/npgeek.css" />
<link rel="stylesheet" href="${cssUrl}" />
</head>

<body>
<header id ="logo">

<img alt="Npgeek logo" src="img/logo.png">

</header>


   
               
         <div>
                <ul>
                    <li><a href="<c:url value="/homePage"/>">home</a></li>
                    <li><a href="<c:url value="/surveyForm"/>">survey</a></li>

                </ul>
            </div>
        </body>
        </html>