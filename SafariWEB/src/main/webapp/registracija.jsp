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

<meta charset="ISO-8859-1">
<title>Registracija</title>
</head>
<div class="center container">
	<img alt="slika"
		src="https://cdn.discordapp.com/attachments/731987479732944970/836919583423332352/839a22fd8c4f4fd2939008dc9b7d7d632.png"
		width="300" height="300">
</div>
<form action="/Safari/kontroler/saveKorisnik" method="post">
	<div class="container">
		<h2>Registruj se</h2>
		<div class="container-fluid row">
			<div class="col-md-4">
				<label for="name">Ime</label> <input type="text"
					class="form-control" name="ime">
			</div>
			<div class="col-md-4">
				<label for="inputPassword">Prezime</label> <input type="text"
					class="form-control" name="prezime">
			</div>
			<div class="col-md-4">
				<label for="JMBG">JMBG</label> <input type="text"
					class="form-control" name="jmbg">
			</div>
		</div>
		<div class="container-fluid row">
			<div class="col-md-4">
				<label for="username">Username</label> <input type="text"
					class="form-control" name="username">
			</div>
			<div class="col-md-4">
				<label for="inputPassword">Password</label> <input type="password"
					class="form-control" name="password">
			</div>
			<div class="col-md-4">
				<label for="JMBG">Adresa</label> <input type="text"
					class="form-control" name="adresa">
			</div>
		</div>
		<div class="container-fluid row">
			<div class="col-md-4">
				<label for="Uloga">Uloga</label> <select name="uloga"
					class="form-control">
					<c:forEach items="${uloge}" var="u">
						<option value="${u.idUloga }">${u.naziv }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br>
		<div class="container row">
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary form-control">Sing
					Up</button>
			</div>
		</div>
	</div>
</form>

</body>
</html>