<%@page import="com.security.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	System.out.println("testing....654");
	String inText = request.getParameter("abc");
	String outText = "";
	if (inText != null && inText.length() > 0) {
//		//outText = TripleDES.encode(inText);
//		outText = TripleDESEncryptor.encryptPassword(inText,"xxx");
		String userID="Test";
		String password=inText;
		String encryptPassword=TripleDes.encryptPassword(userID, password);
		outText = encryptPassword;
	}
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Triple DES</title>
</head>
<body>
	<form>
		<p>
			<input type="text" id="abc" name="abc" size=50>
			<input type="submit" value="Encode">
		</p>
		<p>
			<span><%=outText %></span>
		</p>
	</form>
</body>
</html>