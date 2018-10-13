package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.Etudiants;
import bdd.Groupes;

@WebServlet("/Info")
public class Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Info() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String nomGrp = request.getParameter("nomGrp");
		String numEtu = request.getParameter("numEtu");
		if(nomGrp!=null) {
			Groupes tableGroupe = new Groupes();
			request.setAttribute("infoGroupe", tableGroupe.recupererInfosGroupe(nomGrp));
		}
		if(numEtu!=null) {
			Etudiants tableEtudiant = new Etudiants();
			
			request.setAttribute("infoEtudiant", tableEtudiant.recupererInfosEtudiant(numEtu));
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Info.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logout")!=null) {
			response.sendRedirect("http://localhost:8080/Projet/Authentification");
		}
	}

}
