<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)session.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
onload = function() {
	 encodeURIComponent();
		alert('<%=msg%>')
		location.href="./list"
	}
</script>
<head>
<meta charset="UTF-8">
<title>Trip Of Life</title>
</head>
<body>
</body>
</html>