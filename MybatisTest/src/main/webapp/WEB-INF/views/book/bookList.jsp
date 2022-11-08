<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
	<body>
		<div align="center">
			<div><h1>책 목록보기</h1></div>
			<div>
				<h3>${books }</h3>
				<c:forEach items="${books }" var="b">
					${b.bookCode } : ${b.bookTitle } : ${b.bookAuthor }<br/>
				</c:forEach>
			</div>
		</div>
	</body>
</html>