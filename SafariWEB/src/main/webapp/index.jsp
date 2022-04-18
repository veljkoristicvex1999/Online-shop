<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">
<meta charset="ISO-8859-1">
<title>Pocetna</title>
</head>
<body>

	<div class="sidenav">
		<a href="/Safari/kontroler/getAukcije">Sve aukcije</a>
	</div>
	<form action="/Safari/kontroler/checkInfo" method="post">
		<div class="container">
			<div class="center">
				<img alt="slika"
					src="https://cdn.discordapp.com/attachments/731987479732944970/836919583423332352/839a22fd8c4f4fd2939008dc9b7d7d632.png"
					width="300" height="300">
			</div>
			<h1 style="text-align: center;">Dobrodosli na sajt aukcijske
				kuce Safari</h1>
			<div class="center">

				<div class="mb-3">
					<label for="exampleInputEmail1"">Username </label> <input
						type="text" class="form-control" name="username">
				</div>
				<div class="mb-3">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" name="password">
				</div>
				<button type="submit" class="btn btn-primary">Ulogujte se</button>
				<br> <p style="color: red;">${poruka }</p><br> Ukoliko nemate nalog <a
					href="/Safari/kontroler/getUlogas">Registrujte se</a>
			</div>
		</div>
	</form>
</body>
</html>