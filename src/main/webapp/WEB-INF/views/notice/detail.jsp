<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Notice Detail Page</h1>
<h3>작성자:${dto.writer }</h3>
<h3>작성 날짜:${dto.regDate}</h3>
<h3>글 내용:${dto.contents}</h3>

<a href="./update?num=${dto.num}">글 수정</a>
<a href="./delete?num=${dto.num}">글 삭제</a>
</body>
</html>