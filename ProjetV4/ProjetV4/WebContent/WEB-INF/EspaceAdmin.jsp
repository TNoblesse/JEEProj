<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Auth.css" type="text/css"/>
<title>Espace Admin</title>
</head>
<body>
	<h3>Connecté en tant qu'Administrateur.</h3>
	<form method="post"><input class="logout" type="submit" name="logout" value="Deconnexion"/> </form>
	<form method="post">
		<label for="groupe">Selection du groupe</label>
		<select id="groupe" name="groupe">
			<option></option>
			<c:forEach var="groupe" items="${ groupes }">
				<option><c:out value="${ groupe.nom }"/></option>
			</c:forEach>
		</select>
		<label for="seance">Selection de la séance</label>
		<select id="seance" name="seance">
			<option></option>
			<c:forEach var="seance" items="${ seances }">
				<option><c:out value="${ seance.description }"/></option>
			</c:forEach>
		</select>
		<input type="submit" name="searchcours" value="Afficher liste Etudiants"/>
	</form>
	<c:choose>
		<c:when test="${ empty etudiants }">Pas de cours dans cette configuration.</c:when>
		<c:otherwise>
			<p> <a href="/Projet/Info?nomGrp=${ groupeSelectionne }">${ groupeSelectionne } </a> / ${ seanceSelectionnee }</p>
			<form method="post">
			<table class="table">
				<tr>
	       			<th>Numéro Etudiant</th>
	      			<th>Nom</th>
	      			<th>Prenom</th>
	      			<th>Absent?</th>
 				</tr>
				<c:forEach var="etudiant" items="${ etudiants }">
				<tr>
					<td><a href="/Projet/Info?numEtu=${ etudiant.num }">${ etudiant.num }</a></td>
					<td><c:out value="${ etudiant.nom }"/></td>
					<td><c:out value="${ etudiant.prenom }"/></td>
					<td>
						<input type="checkbox" name="AJ${ etudiant.num }" value="AbsentJ"/><label for="checkbox">Absent justifié</label>
						<input type="checkbox" name="ANJ${ etudiant.num }" value="AbsentNJ"/><label for="checkbox">Absent non justifié</label>
					</td>
				</tr>
				</c:forEach>
			</table>
			<input type="submit" name="commit" value="Enregistrer les modifications."/>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>