<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>







<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>揪團圖片資料 - listOneEmp.jsp</title>

<%@ include file="included-fragment.file" %>
<style type="text/css">
	
    span {
		display: inline-block;
		width: 150px;
		text-align: left;
		font-weight: bold;
	}
</style>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-success fixed-top justify-content-center">
		 <div align="center"> <h2>揪團圖片資料 - listOneAct.jsp</h2>
		 <h3><a class="navbar-brand" href="<%=request.getContextPath()%>/back-end/act/select_page.jsp"><img src="<%=request.getContextPath()%>/resources/images/back1.gif">回查詢頁${success}</a></h3></div>
</nav>

	<div align="center">
		<h3><span>查詢結果 :</span></h3>
		<span>揪團圖片編號:</span><span>${actVO.actPicNo}</span><br/>
		<span>揪團圖片名稱:</span><span>${actVO.actPicName}</span><br/>
		<span>照片:</span><span><img src="<%=request.getContextPath()%>/act/DBGifReader?actPicNo=${actVO.actPicNo}" width="100px"></span><br/>
	</div>


</body>
</html>