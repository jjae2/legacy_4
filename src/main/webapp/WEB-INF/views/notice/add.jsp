<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Notice Add Page</h1>

<form action="./add" method="post">
Title<input type="text" name="title">
Contents<input type="text" name="contents">
Writer<input type="text" name="writer">
Hit<input type="number" name="hit">
<input type="submit" name="insert">
<button type="submit">ADD</button>
</form>
</body>
</html>