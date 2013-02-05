<%@ page language="java" contentType="text/html; charset=US-ASCII" import="com.glroland.weather.business.*"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Forecast</title>
</head>
<body>

<%
  boolean formSubmitted = true;
  String zipCode = request.getParameter("zipCode");
  if (zipCode == null)
  {
	  zipCode = "";
	  formSubmitted = false;
  }
%>

<form>
Zip Code: <input name="zipCode" value="<%= zipCode %>" /><br/>
<br/>
<input type="submit" />
</form>

<%
  if (formSubmitted)
  {
	ForecastService service = new ForecastService();
	String temp = service.getTempForZip(zipCode);
%>

<br/>
<br/>

Temperature is <%= temp %>.</br>

<%
  }
%>
</body>
</html>