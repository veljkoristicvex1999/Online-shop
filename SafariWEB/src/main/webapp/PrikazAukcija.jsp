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
<title>Prikaz svih aukcija</title>
</head>
<body>
	<div class="container">
	<p align="right" style="margin-top: 10"><a href="/Safari/index.jsp"><button class="btn btn-outline-primary">Prijavite se</button></a></p>
		<div class="center">
			<a href="/Safari/index.jsp"><img alt="slika"
				src="https://cdn.discordapp.com/attachments/731987479732944970/836919583423332352/839a22fd8c4f4fd2939008dc9b7d7d632.png"
				width="300" height="300"></a>
		</div>
		<div class="center">
			<form action="/Safari/kontroler/filter1" method="get">
				<div class="row">
					<div class="col-md-3">
						<select name="kat" class="form-control">
							<option selected="selected" hidden="hidden" value="0">Kategorija</option>
							<c:forEach items="${kategorije }" var="k">
								<option value="${k.idKategorija }">${k.naziv }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-3">
						<select name="st" class="form-control">
							<option selected="selected" hidden="hidden" value="0">Stanja</option>
							<c:forEach items="${stanja }" var="s">
								<option value="${s.idStanje }">${s.opis }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-3">
						<input type="text" name="naziv" class="form-control"
							placeholder="Naziv">
					</div>
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary form-control">Pretrazi</button>
					</div>
				</div>
			</form>
		</div>
		<br>
		<div class="row">
			<c:forEach items="${predmeti }" var="p">
				<div class="col-md-4 product-item">
					<div class="image">
						<img src="${p.slikaUrl }" width="200" height="200">
					</div>
					<div>${p.kategorija.naziv }</div>
					<div>${p.stanje.opis }</div>
					<div>${p.naziv }</div>
					<div>${p.opis }</div>
					<div>${p.rokZavAuk }</div>
					<div>${p.pocetnaCena }$</div>
					<div>Vlasnik: ${p.korisnik.ime } ${p.korisnik.prezime }</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>