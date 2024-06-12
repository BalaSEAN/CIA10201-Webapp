<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>index.jsp - Java Servlet + JSP</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/webjars/bootstrap/4.2.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/index.css"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/out-statics/out-css/style.css"/>
<script src="<%=request.getContextPath()%>/resources/out-statics/out-js/app2.js"></script>
</head>
<body>
<% String message = request.getParameter("name"); %> <% if (message==null) message="世界你好"; pageContext.setAttribute("message", message);%>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top justify-content-center" onclick="changeColor()">
		 <div align="center"><h2>
		 <span class="hello-color">Hello, ${message} : </span> Java Servlet + JSP 
		 （
		    <img width="100px" height="80px" src="<%=request.getContextPath()%>/resources/images/java-servlet.jpg"> + 
		    <img width="100px" height="80px" src="<%=request.getContextPath()%>/resources/images/jsp.png">
		 ）
		 </h2><h3><a class="navbar-brand" href="https://jakarta.ee">
		 JavaEE9 官網 : https://jakarta.ee</a></h3></div>
</nav>

	
	<main role="main" class="container">
       <div class="starter-template">
           <span class="color1" id="title1">前端展示層(View) - 使用Java官方的JSP技術</span>
           <h1>
               
               <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web.html" class="index-green">Java Servlet</a> <font color=black>+</font>
               <a href="https://www.tibame.com/course/33" class="index-green" id="title2">JSP</a> ->&nbsp;
               <a href="https://www.tibame.com/course/33">Java WebApp</a>
           </h1><hr><br>
           <h3><a href="<%=request.getContextPath()%>/back-end/act/select_page.jsp" style="text-decoration:underline;color: black;">Click here to Java Servlet + JSP Project</a></h3>
       </div>
       <% 
           List<String> myList = Arrays.asList("基礎 Java","JDBC 資料庫連結", "Java Servlet", "JSP (JavaServer Pages)", "Java WebApp (<font color=red>完成 Java Web MVC</font>)");
           pageContext.setAttribute("myList", myList);
       %>
       <div style="float: right;left: -80.5%;position: relative;">
         學習順序:
         <ol style="float: left;left: 100%;position: relative;">
			 <c:forEach var="listValue" items="${myList}">
				<li>${listValue}</li>
			 </c:forEach>
	     </ol>
	   </div>
    </main>

</body>
</html>