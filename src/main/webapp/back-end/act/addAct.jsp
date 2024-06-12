<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.act.model.*"%>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
   ActVO actVO = (ActVO) request.getAttribute("actVO");
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>圖片資料新增 - addAct.jsp</title>

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
		margin: 5px;
		text-align: left;		
	}
	input[type=text], input[type=password], select, textarea {
		width: 200px;
		margin: 5px;
	}
	form div div {
		display: table-cell;
	}
	.center {
        margin-left: auto;
        margin-right: auto;
    }
</style>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-success fixed-top justify-content-center">
		 <div align="center"> <h2>圖片資料新增 - addAct.jsp</h2>
		 <h3><a class="navbar-brand" href="<%=request.getContextPath()%>/back-end/act/select_page.jsp"><img src="<%=request.getContextPath()%>/resources/images/back1.gif">回查詢頁</a></h3></div>
</nav>

	<div align="center">
		<h3><b>所有欄位皆為必填欄位</b></h3>
		<form action="<%=request.getContextPath()%>/act/act.do" method="post" enctype="multipart/form-data">
						

			<div>
			<label for="actNo">揪團編號:</label>
			<input id ="actNo" name="actNo" type="text" value="${param.actNo}" onclick="hideContent('actNo.errors');" />
			<span  id ="actNo.errors" class="error">${errorMsgs.actNo}<br/></span>
			</div>			
			
			
			<div>
			<label for="actPic">圖片名稱:</label>
			<input id ="actPic" name="actPic" type="text" value="${param.actPic}" onclick="hideContent('actPic.errors');" />
			<span  id ="actPic.errors" class="error">${errorMsgs.actPic}<br/></span>
			</div>
						
			

            <div>
			<label for="actPicType">照片:</label>
			<input id ="actPicType" name="actPicType" type="file" onclick="previewImage()" multiple="multiple" onchange="hideContent('actPicType.errors');" />
			<span  id ="actPicType.errors" class="error">${errorMsgs.upFiles}</span>
			<div id="blob_holder"></div>
			</div>
			
			<div>
				<div></div>
				<input  type="hidden" name="action" value="insert">
				<button type="submit" id="submit"> 送出新增 </button>
				<div></div>
			</div>
		</form>
	</div>


<!-- JavaScript part -->
<script type="text/javascript">
//清除提示信息
function hideContent(d) {
     document.getElementById(d).style.display = "none";
}

//照片上傳-預覽用
var filereader_support = typeof FileReader != 'undefined';
if (!filereader_support) {
	alert("No FileReader support");
}
acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
};
function previewImage() {
	var upfile1 = document.getElementById("upFiles");
	upfile1.addEventListener("change", function(event) {
		var files = event.target.files || event.dataTransfer.files;
		for (var i = 0; i < files.length; i++) {
			previewfile(files[i])
		}
	}, false);
}
function previewfile(file) {
	if (filereader_support === true && acceptedTypes[file.type] === true) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var image = new Image();
			image.src = event.target.result;
			image.width = 100;
			image.height = 75;
			image.border = 2;
			if (blob_holder.hasChildNodes()) {
				blob_holder.removeChild(blob_holder.childNodes[0]);
			}
			blob_holder.appendChild(image);
		};
		reader.readAsDataURL(file);
		document.getElementById('submit').disabled = false;
	} else {
		blob_holder.innerHTML = "<div  style='text-align: left;'>" + "● filename: " + file.name
				+ "<br>" + "● ContentTyp: " + file.type
				+ "<br>" + "● size: " + file.size + "bytes"
				+ "<br>" + "● 上傳ContentType限制: <b> <font color=red>image/png、image/jpeg、image/gif </font></b></div>";
		document.getElementById('submit').disabled = true;
	}
}
</script>

</body>
</html>