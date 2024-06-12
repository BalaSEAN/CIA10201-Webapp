<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>







<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>揪團圖片管理 - select_page.jsp</title>

<%@ include file="included-fragment.file" %>
<style type="text/css">
	
	button {
		padding: 5px;
	}
	form {
		display: table;
	}
	form div {
		display: table-row;
	}
	label, input, span, select {
		display: table-cell;
		margin: 2px;
		text-align: left;		
	}
	input[type=text], input[type=password], select, textarea {
		width: 200px;
		margin: 2px;
	}
	form div div {
		display: table-cell;
	}
	.center {
        margin-left: auto;
        margin-right: auto;
    }
    span {
		display: inline-block;
		width: 150px;
		text-align: left;
		font-weight: bold;
	}
	div.a {
        display: inline-block;
        width: 50%;
        height: auto;
        padding: 5px;
        border: 0px solid blue;    
        background-color: white; 
    }
    div.b {
        position: absolute; 
        bottom: 30%; 
        right: 2%; 
        width: 45%;
        padding: 5px; 
        border: 1px solid blue;
    }
</style>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-success fixed-top justify-content-center">
		 <div align="center"> <h2>揪團圖片管理 - select_page.jsp</h2>
		 <h3><a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/resources/images/back1.gif">回index.jsp</a></h3></div>
</nav>

	<h4><span>資料查詢:</span></h4>

	<div></div><br>
	<div class="a">
	  <ul>
		<li><h5><a href='<%=request.getContextPath()%>/back-end/act/listAllAct.jsp'>List</a> all Acts. <br><br></h5></li>

		<li>
			<form method="post" action="<%=request.getContextPath()%>/act/act.do">
				輸入圖片編號:
				<input type="text" name="actPicNo"><font color=red>${errorMsgs.actPicNo}</font>
                <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出"> (輸入如1)
			</form><br>
		</li>

        <jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />

		<li><form method="post" action="<%=request.getContextPath()%>/act/act.do">
				選擇圖片編號:
				<select size="1" name="actPicNo">
                  <c:forEach var="actVO" items="${actSvc.all}" > 
                    <option value="${actVO.actPicNo}">${actVO.actPicNo}
                  </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form><br>
		</li>

		<li><form method="post" action="<%=request.getContextPath()%>/act/act.do">
				選擇圖片名稱:
				<select size="1" name="actPicNo">
                  <c:forEach var="actVO" items="${actSvc.all}" > 
                    <option value="${actVO.actPicNo}">${actVO.actPicName}
                  </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form><br>
		</li>
		</ul>
	
	<h4><span>圖片管理</span></h4>
	  <ul>
		  <li><h5><a href='<%=request.getContextPath()%>/back-end/act/addAct.jsp'>Add</a> a new Act.</h5></li>
	  </ul>
	</div>

    <c:if test="${getOne_For_Display}"><!-- 旗標getOne_For_Display見EmpServlet.java的第74行 -->
         <div class="b">
	      <%@ include file="listOneAct-div-fragment.file" %> <%-- 或(也可) <jsp:include page="listOneAct.jsp" /> --%>
	     </div>  
    </c:if>
<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>

  
</body>
</html>