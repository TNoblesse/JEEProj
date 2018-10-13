package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.ConnectionForm;
import bdd.Etudiants;
import bdd.Groupes;
import bdd.Seances;
import main.Etudiant;

@WebServlet("/Authentification")
public class Authentification extends HttpServlet {
	String nomGroupe=null;
	String numSeance=null;
	private static final long serialVersionUID = 1L;
       
    public Authentification() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/Authentification.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("signin")!=null) {
			ConnectionForm form = new ConnectionForm();
			form.verifierIdentifiants(request);
			
			request.setAttribute("form", form);
	
			if(form.getResult().contains("admin")) {
				Groupes tableGroupes = new Groupes();
				Seances tableSeances = new Seances();
				request.setAttribute("groupes", tableGroupes.recupererGroupes());
				request.setAttribute("seances", tableSeances.recupererSeances());
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin.jsp");
				rd.forward(request, response);
			}
			else if(form.getResult().contains("etudiant")) {
				response.sendRedirect("http://localhost:8080/Projet/Info?numEtu="+form.getResult().substring(form.getResult().indexOf("E")+1, form.getResult().indexOf("C")));
			}
			else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/Authentification.jsp").forward(request, response);
			}
		}
		else if(request.getParameter("searchcours")!=null) {
			String nomGroupe = request.getParameter("groupe");
			String seance = request.getParameter("seance");
			String numSeance= seance.substring(1, seance.indexOf(")"));
			this.nomGroupe=nomGroupe;
			this.numSeance=numSeance;
			request.setAttribute("groupeSelectionne", nomGroupe );
			request.setAttribute("seanceSelectionnee", seance );
			Groupes tableGroupes = new Groupes();
			Seances tableSeances = new Seances();
			request.setAttribute("groupes", tableGroupes.recupererGroupes());
			request.setAttribute("seances", tableSeances.recupererSeances());
			Etudiants tableEtudiants = new Etudiants();
			request.setAttribute("etudiants", tableEtudiants.recupererEtudiants(request));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("commit")!=null){
			
			Groupes tableGroupes = new Groupes();
			Seances tableSeances = new Seances();
			request.setAttribute("groupes", tableGroupes.recupererGroupes());
			request.setAttribute("seances", tableSeances.recupererSeances());
			Etudiants enregistrerEtudiants = new Etudiants();
			request.setAttribute("nomGroupe", this.nomGroupe);
			request.setAttribute("numSeance", this.numSeance);
			enregistrerEtudiants.ajouterEtudiantsAbsents(request);
			this.nomGroupe=null;
			this.numSeance=null;
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/EspaceAdmin.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("logout")!=null) {
			response.sendRedirect("http://localhost:8080/Projet/Authentification");
		}
	}
}
