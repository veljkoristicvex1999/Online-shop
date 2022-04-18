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
<title>Prikaz Licitacija</title>
</head>
<body>

	<div class="container">
		<div class="center">
			<a href="/Safari/regIndex.jsp"> <img alt="slika"
				src="https://cdn.discordapp.com/attachments/731987479732944970/836919583423332352/839a22fd8c4f4fd2939008dc9b7d7d632.png"
				width="300" height="300"></a>
		</div>
		<div class="row">
			<div class="col-md-4 product-item">
				<div class="image">
					<img src="${pr.slikaUrl }" width="200" height="200">
				</div>
				<div>${pr.kategorija.naziv }</div>
				<div>${pr.stanje.opis }</div>
				<div>${pr.naziv }</div>
				<div>${pr.opis }</div>
				<div>${pr.rokZavAuk }</div>
				<div>${pr.pocetnaCena }$</div>
				<div>Vlasnik: ${pr.korisnik.ime } ${pr.korisnik.prezime }</div>
			</div>
			<div class="col-md-4 center">
				<h3>Trenutna lista licitacija</h3>
				<table class="table table-secondary">
					<tr>
						<th scope="col">Iznos</th>
						<th scope="col">Ucesnik</th>
					</tr>
					<c:forEach items="${licitacije }" var="l">
						<tr>
							<td scope="row">${l.iznos }</td>
							<td scope="row">${l.korisnik.ime } ${l.korisnik.prezime }</td>

						</tr>
					</c:forEach>
				</table>
				<form action="/Safari/kontroler/dodajPonudu" method="post">
					<label for="ponuda">Ukoliko zelite da licitirate, ovde
						unesite cenu:</label> <input type="number" step="any" class="form-control"
						name="ponuda" min=${najveca } required>
					<button type="submit" class="btn btn-primary form-control">Dodaj
						ponudu</button>
				</form>
			</div>
		</div>
	</div>



</body>
</html>