<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ip查询</title>
<style type="text/css">
#d1{
width: 200px;
height: 500px;
text-align: center;
margin-left: 200px;
}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
</head>
<script src="<%=request.getContextPath()%>/layui/layui.js"
	charset="utf-8"></script>
<body>
<div id="d1">
	<div>
		<h1>输入ip地址</h1>
			<form  action="selectServlet" method="post">
				<input type="text" name="IPAddress" /><br> 
				<button type="submit" class="layui-btn">查询</button>
			</form>
	</div>

	<div>
		<p>查询结果</p>
		${IPLocation }
	</div>
</div>	

	
</body>
</html>