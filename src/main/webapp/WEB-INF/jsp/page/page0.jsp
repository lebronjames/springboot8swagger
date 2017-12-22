<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Spring Boot Sample</title>
</head>

<body>
    <img alt="读取默认配置中的图片" src="${pageContext.request.contextPath }/pic1.jpg">
    <br/>
    <img alt="读取自定义配置中的图片" src="${pageContext.request.contextPath }/img/pic2.jpg">
</body>
</html>