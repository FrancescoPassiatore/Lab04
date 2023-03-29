package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
private CorsoDAO corsoDAO;
private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiCorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}

	public List<Studente> getTuttiStudenti(){
		return this.studenteDAO.getTuttiStudenti();
	}
	
	public Studente getStudente(String mat) {
		return this.studenteDAO.getStudente(mat);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiPerMatricola (String mat){
		return this.corsoDAO.getCorsiPerMatricola(mat);
	}
	
	public boolean inscriviStudenteACorso(String studente, String corso) {
		return this.corsoDAO.inscriviStudenteACorso(studente, corso);
	}
}
