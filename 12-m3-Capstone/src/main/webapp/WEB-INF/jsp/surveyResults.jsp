<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="pageTitle" value="survey result"/>
<%@include file="header.jsp" %>


<h1>Favorite Parks</h1>

<body>
<table class="table">
<c:forEach var="survey" items="${results}">
<tr>
	<td><c:set var="lowercase" value="${fn:toLowerCase(survey.parkCode)}"/> <c:url var="imageURL" value="/img/parks/${lowercase}.jpg"/> <img src = "${imageURL}"/></td>
	<td><c:out value="${survey.parkName}" /></td> 
	<td><c:out value="${survey.parkCount} Votes!" /></td>
	
</tr>
	
	</c:forEach>
	</table>
		
	
	</body>