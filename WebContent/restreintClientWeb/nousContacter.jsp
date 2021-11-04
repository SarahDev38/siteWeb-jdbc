<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/inc/style.css" />" />
<title>nous contacter</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content">
<form action="mailto:sarah.silvestre38@gmail.com" method="POST" enctype="multipart/form-data" name="EmailForm">
	Name:<br> <input type="text" size="19" name="ContactName"><br><br>
	Message:<br> 
		<textarea name="ContactComment" rows="15" cols="80"></textarea><br><br> 
	<input type="submit" value="Envoyer"> 
</form>
</div>
</body>
</html>