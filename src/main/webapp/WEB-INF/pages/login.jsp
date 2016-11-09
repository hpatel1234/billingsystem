<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<c:url value="/assets/js/lib/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/lib/jquery.validate.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/common.css"/>">
<body>
<div class="container panel panel-primary" style="width: 300px;margin-top: 10%;padding: 0px">
	 <div class="panel-heading"><span style="font-size: large;">Sign In</span></div>
	 <div class="panel-body">
	<form autocomplete="off" id="loginForm" method="post" action="<%=request.getContextPath()%>/user/login">
		<div class="form-group">
			<input type="email" class="form-control" id="email" name="email" placeholder="Email">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
		</div>
		<button type="submit" class="btn btn-primary form-control">Login</button>
	 </form>
	 </div>
</div>
</body>
</html>