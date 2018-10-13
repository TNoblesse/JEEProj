<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Auth.css" type="text/css"/>
<title>More Info</title>
</head>
<body>
	<form method="post"><input class="logout" type="submit" name="logout" value="Deconnexion"/> </form>
	<c:choose>
		<c:when test="${ ! empty infoEtudiant }"> 
		<h3>Informations générales</h3>
		<ul>
			<li>Numéro Etudiant: ${ infoEtudiant.num }</li>
			<li>Nom Etudiant: ${ infoEtudiant.nom }</li>
			<li>Prenom Etudiant: ${ infoEtudiant.prenom }</li>
		</ul>
		<h3>Absences</h3>
		<table class="table">
				<tr>
	       			<th>Absence justifiée</th>
	      			<th>Seance</th>
	      			<th>Groupe</th>
	      			<th>Nom Professeur</th>
	      			<th>Prenom Professeur</th>
	      			<th>Matière</th>
 				</tr>
				<c:forEach var="absence" items="${ infoEtudiant.listeAbscence }">
				<tr>
					<td><c:out value="${ absence.justifiee }"/></td>
					<td><c:out value="${ absence.cours.seance.description }"/></td>
					<td><c:out value="${ absence.cours.groupe.nom }"/></td>
					<td><c:out value="${ absence.cours.prof.nom }"/></td>
					<td><c:out value="${ absence.cours.prof.prenom }"/></td>
					<td><c:out value="${ absence.cours.matiere.nom }"/></td>
				</tr>
				</c:forEach>
		</table>
		<h3>Résultats</h3>
		<table class="table">
				<tr>
	       			<th>Matiere</th>
	      			<th>Note</th>
 				</tr>
				<c:forEach var="resultat" items="${ infoEtudiant.listeResultat }">
				<tr>
					<td><c:out value="${ resultat.matiere.nom }"/></td>
					<td><c:out value="${ resultat.note }"/></td>
				</tr>
				</c:forEach>
		</table>
		</c:when>
		<c:when test="${ ! empty infoGroupe }">
			<h3>Informations générales</h3>
		<ul>
			<li>Nom Groupe: ${ infoGroupe.nom }</li>
			<li>Semestre : ${ infoGroupe.semestre }</li>
			<li>Annee : ${ infoGroupe.annee }</li>
		</ul>
		<h3>Etudiants du groupe</h3>
		<table class="table">
				<tr>
					<th>Numéro </th>
	      			<th>Nom </th>
	      			<th>Prenom </th>
 				</tr>
				<c:forEach var="etudiant" items="${ infoGroupe.listeEtudiant }">
				<tr>
					<td><a href="/Projet/Info?numEtu=${ etudiant.num }"><c:out value="${ etudiant.num }"/></a></td>
					<td><c:out value="${ etudiant.nom }"/></td>
					<td><c:out value="${ etudiant.prenom }"/></td>
				</tr>
				</c:forEach>
		</table>
		<h3>Cours du groupe</h3>
		<table class="table">
				<tr>
	      			<th>Seance</th>
	      			<th>Nom Professeur</th>
	      			<th>Prenom Professeur</th>
	      			<th>Matière</th>
 				</tr>
				<c:forEach var="cours" items="${ infoGroupe.listeCours }">
				<tr>
					<td><c:out value="${ cours.seance.description }"/></td>
					<td><c:out value="${ cours.prof.nom }"/></td>
					<td><c:out value="${ cours.prof.prenom }"/></td>
					<td><c:out value="${ cours.matiere.nom }"/></td>
				</tr>
				</c:forEach>
		</table>
		</c:when>
	</c:choose>
</body>
</html>