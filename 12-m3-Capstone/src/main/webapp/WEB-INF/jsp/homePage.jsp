<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="pageTitle" value="home page"/>
<%@include file="header.jsp" %>

<h2>National Parks</h2>

<table style="width:100%">

<c:forEach var="park" items="${parks}">
<tr>
	
    <td><c:set var="lowercase" value="${fn:toLowerCase(park.parkCode)}"/> <c:url var="imageURL" value="/img/parks/${lowercase}.jpg"/> <img src = "${imageURL}"/></td>
    <c:url var="detail" value="/detailPage"><c:param name="parkCode">${park.parkCode}</c:param></c:url>
	<td><a href="${detail}"><c:out value="${park.parkName}" /></a></td>
	<td><c:out value="${park.parkDescription}" /></td>
	
</tr>
	
	</c:forEach>
	
 
  
  