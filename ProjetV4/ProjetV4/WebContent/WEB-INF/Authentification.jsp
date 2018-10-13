<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="Auth.css" type="text/css"/>
<title>Bienvenue</title>
</head>
<body>
	<h2>Bienvenue au centre de controle d'absence.</h2>
	<p>
		 ${ form.result }
	</p>
	<form method="post" action=>
		<p>
		<label for="nom">Nom d'utilisateur: </label>
		<input type="text" id="nom" name="nom" />
		</p>
		<p>
		<label for="password">Mot de passe: </label>
		<input type="password" id="pass" name="pass" />
		</p>
		<input type="submit" name="signin" value="Connexion"/>
	</form>
</body>
</html>