<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="pageTitle" value="home page"/>
<%@include file="header.jsp" %>


<body>

<head>
<c:url var="cssUrl" value="/npgeek.css" />
<link rel="stylesheet" href="${cssUrl}" />
</head>


<table style="width:100%">
	<tr>
	<td><c:set var="lowercase" value="${fn:toLowerCase(park.parkCode)}"/> <c:url var="imageURL" value="/img/parks/${lowercase}.jpg"/> <img src = "${imageURL}"/></td>
	</tr>
</table>


<table style="width:100%">
	<tr>
	<th><c:out value="${park.parkName}" /></th>
	</tr>
</table>

<table style="width:100%">
	
		
	<tr>
   <td><c:out value="${park.parkDescription}" /></td>
	
	</tr>
</table>


	<ul>
	<li><c:out value="State: ${park.state}" /></li>
	<li><c:out value="Acres: ${park.acreage}"/></li>
	<li><c:out value="Elevation: ${park.elevationInFeet}" /></li>
	<li><c:out value="Miles of Trail: ${park.milesOfTrail}" /></li>
	<li><c:out value="Campsites: ${park.numberOfCampsites}" /></li>
	<li><c:out value="Climate: ${park.climate}" /></li>
	<li><c:out value="Founded in: ${park.yearFounded}" /></li>
	<li><c:out value=" Annual Visitors: ${park.annualVisitorCount}" /></li>
	<li><c:out value="Entry Fee: ${park.entryFee}" /></li>
	<li><c:out value="Animal Species: ${park.numberOfAnimalSpecies}" /></li>
	</ul>


<table style="width:100%">
<tr>
<td><c:out value="${park.inspirationalQuote}" /></td>
	</tr>
</table>
<table style="width:100%">
<tr>
<td><c:out value="${park.inspirationalQuoteSource}" /></td>
	</tr>
</table>



<table>
	
		
	<tr>
	<c:forEach var="weather" items="${weathers}">
	
	<td><c:set var="lowercase" value="${fn:toLowerCase(weather.forecast)}"/> <c:url var="imageURL" value="/img/weather/${lowercase}.png"/> <img src = "${imageURL}"/>
	
	<c:out value="Low Temp: ${weather.low}" />
	<c:out value="High Temp: ${weather.high}" />
	
	><c:out value="Forecast: ${weather.forecast}" /></td>
	</c:forEach>
	</tr>
</table>  



</body>



	
	
	
	
	
	
	
	
		