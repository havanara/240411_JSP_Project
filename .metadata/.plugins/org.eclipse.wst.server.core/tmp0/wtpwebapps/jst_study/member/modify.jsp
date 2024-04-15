
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board modify Page</h1>
	<form action="/memb/update" method="post">
		<table border="1">
			<tr>
				<th>id</th>
				<td><input type="text" name="id" value="${mvo.id }"></td>
			</tr>
			<tr>
				<th>pwd</th>
				<td><input type="text" name="pwd" value="${mvo.pwd } "></td>
			</tr>
			<tr>
				<th>email</th>
				<td><input type="text" name="email" value="${mvo.email }"></td>
			</tr>
			<tr>
				<th>age</th>
				<td><input type="text" name="age" value="${mvo.age }"></td>
			</tr>
			<tr>
				<th>phone</th>
				<td><input type="text" name="phone" value="${mvo.phone }"></td>
			</tr>
		</table>
		<button type="submit">전송</button>
	</form>
	<a href="/memb/modify?id=${mvo.id }"><button>modify</button></a>
	<a href="/memb/remove?id=${mvo.id }"><button>delete</button></a>
	<a href="/memb/list"><button>list</button></a>
</body>
</html>