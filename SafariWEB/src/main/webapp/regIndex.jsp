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
<title>Dobrodosli</title>
</head>
<body>

	<div class="sidenav">
		<a href="/Safari/kontroler/getInfo">Postavljanje predmeta na
			aukciju</a> <a href="/Safari/kontroler/svojeAukcije">Vase aukcije</a>
	</div>
	<p align="right"><a href="/Safari/kontroler/logOut"><button type="submit" class="btn btn-outline-primary">Odjavi se</button></a></p>
	<div class="center">
		<img alt="slika"
			src="https://cdn.discordapp.com/attachments/731987479732944970/836919583423332352/839a22fd8c4f4fd2939008dc9b7d7d632.png"
			width="300" height="300">
	</div>
	<div class="container center">
		<div style="margin-top: 20px" class="container-fludi">
			<h2>Dobrodosli ${korisnik.ime } ${korisnik.prezime }</h2>
		</div>
		<form action="/Safari/kontroler/filter" method="get">
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
		<br>
		<div class="row">
			<c:forEach items="${predmeti }" var="p" varStatus="i">
				<div class="col-md-4 product-item">
					<div class="image">
						<img src="${p.slikaUrl }" width="200" height="200">
					</div>
					<div><b>${p.naziv }</b></div>
					<div>${p.opis }</div>
					<div>${p.stanje.opis }</div>
					<div>${p.kategorija.naziv }</div>
					<div>${p.rokZavAuk }</div>
					<div>Pocetna cena: ${p.pocetnaCena }$</div>
					<div>Najveca ponuda: ${najveceCene[i.index] }$</div>
					<div>Vlasnik: ${p.korisnik.ime } ${p.korisnik.prezime }</div>
					<a href="/Safari/kontroler/getLicitacije?idPr=${p.idPredmet }"><button
							type="submit" class="btn btn-primary form-control"<c:if test="${p.korisnik.idKorisnik eq korisnik.idKorisnik }"><c:out value="hidden='hidden'" /></c:if>">Licitiraj
						</button></a>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>