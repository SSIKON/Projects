<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="survey form"/>
<%@include file="header.jsp" %>


<h1>Daily Survey</h1>
<html>
<body>
<c:url var="addSurveyFormUrl" value="/surveyForm" />

<form action="${addSurveyFormUrl}" method="POST">
	<label for="parkName">Park Name:</label>
	<select id="selectedPark" name="parkCode">

            <c:forEach var="park" items="${allParks}">

                <option value="${park.parkCode}">${park.parkName}</option>

            </c:forEach>
            </select>
	<label for="email">email:</label>
	
	<input type="text" name="emailAddress" /><br />
	
	<label for="residenceState">state of residence:</label>
	<select id="selectedState" name="state">

            <c:forEach var="state" items="${stateList}">

                <option value="${state}">${state}</option>
			
            </c:forEach>
            </select>
            
	 <label for="activityLevel">activity level:</label>
	<input checked="checked" 
        name="activityLevel" 
        type="radio" 
        value="easy" />
        <c:out value= "easy" />

		<input checked="checked" 
        name="activityLevel" 
        type="radio" 
        value="moderate" />
        <c:out value= "moderate" />
        
        <input checked="checked" 
        name="activityLevel" 
        type="radio" 
        value="difficult" />
        <c:out value= "difficult" /> 
        
        <%-- <label path="activityLevel">activity level:</label>
        <form:radiobutton path="activityLevel" value="easy" label="easy"/> --%>
        
        
	<input type="submit" value="Submit Survey" />

</form>
</body>
</html>