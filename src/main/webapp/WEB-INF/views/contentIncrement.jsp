<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trip of Life</title>
</head>
<body>

<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	int currentPage = Integer.parseInt(request.getParameter("currentPage"));

	response.sendRedirect("contentView.nhn?idx=" + idx + "&currentPage=" + currentPage);
%>

</body>
</html>