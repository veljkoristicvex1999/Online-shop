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
<title>Dodavanje predmeta</title>
</head>
<body>
	<form action="/Safari/kontroler/savePredmet" method="post">
	<div class="container">
		<p align="right"><a href="/Safari/kontroler/logOut"><button type="submit" class="btn btn-outline-primary">Odjavi se</button></a></p>
	
	<div class="center">
		<a href="/Safari/regIndex.jsp" ><img alt="slika"
				src="https://cdn.discordapp.com/attachments/731987479732944970/836919583423332352/839a22fd8c4f4fd2939008dc9b7d7d632.png"
				width="300" height="300"></a>
		</div>
		<h2>Dodavanje novog predmeta na aukciju</h2>
		<div class="container-fluid row">
			<div class="col-md-6">
				<label for="naziv">Naziv</label> <input type="text"
					class="form-control" name="naziv" required>
			</div>
			<div class="col-md-6">
				<label for="pocetnaCena">Pocetna cena</label> <input type="number"
					class="form-control" name="pocetnaCena" required>
			</div>
		</div>
		<div class="container-fluid row">
			<label for="opis">Opis predmeta</label>
			<textarea class="form-control" id="opis" name="opis" rows="3"></textarea>
		</div>
		<div class="container-fluid row">
			<div class="col-md-6">
				<label for="rokZavAuk">Rok zavrsetka aukcije</label> <input type="date"
					class="form-control" name="rokZavAuk" required>
			</div>
			<div class="col-md-6">
				<label for="slikaUrl">URL slike</label> <input type="text"
					class="form-control" name="slikaUrl" >
			</div>
		</div>
		<div class="container-fluid row">
			<div class="col-md-6">
				<label for="stanje">Stanje</label> <select name="stanje"
					class="form-control">
					<c:forEach items="${stanja}" var="s">
						<option value="${s.idStanje }">${s.opis }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-6">
				<label for="kategorija">Kategorija</label> <select name="kategorija"
					class="form-control">
					<c:forEach items="${kategorije}" var="k">
						<option value="${k.idKategorija }">${k.naziv }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<br>
		<div class="container row">
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary form-control">Postavi predmet</button>
			</div>
		</div>
		<br>
		<c:if test="${!empty prk }">
			${prk } <a href="/Safari/regIndex.jsp">Vidi aukcije</a>
		</c:if>
	</div>
</form>
</body>
</html>