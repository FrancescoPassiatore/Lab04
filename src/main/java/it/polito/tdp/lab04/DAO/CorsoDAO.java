package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso (codins,numeroCrediti,nome,periodoDidattico);
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "SELECT s.matricola , s.nome , s.cognome ,s.CDS "
				   +"FROM studente s , iscrizione i , corso c "
				   +"WHERE  c.nome = ? AND   c.codins = i.codins AND  s.matricola = i.matricola " ;
		
		List<Studente> studentiIscrittiCorso = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, corso.getNome());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String matricola = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				Studente s = new Studente (matricola,cognome,nome,cds);
				
				studentiIscrittiCorso.add(s);

				
			}

			conn.close();
			
			return studentiIscrittiCorso;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
		
		
	}

	//Data una matricola do i corsi 
	
		public List<Corso> getCorsiPerMatricola (String mat){
		
			final String sql = "SELECT c.nome , c.crediti , c.codins, c.pd "
							+"FROM corso c , studente s ,iscrizione i "
							+"WHERE  s.matricola = ? AND s.matricola = i.matricola AND i.codins = c.codins " ;
			
			List<Corso> corsiIscrittoMat = new ArrayList<Corso>();
			
			try {
				
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,mat);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {

					String codins = rs.getString("codins");
					int numeroCrediti = rs.getInt("crediti");
					String nome = rs.getString("nome");
					int periodoDidattico = rs.getInt("pd");

					Corso c = new Corso (codins,numeroCrediti,nome,periodoDidattico);
					
					corsiIscrittoMat.add(c);
				}

				conn.close();
				
				return corsiIscrittoMat;
				

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
			}
			
			
			
		}
	
	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(String studente, String corso) {
		
		final String sql = "SELECT s.matricola, c.codins "
					+"FROM corso c , studente s ,iscrizione i "
					+"WHERE i.matricola = ? AND c.nome = ? AND i.codins = c.codins ";
		
		try {
			boolean controllo = false;
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,studente);
			st.setString(2,corso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if (rs != null) {
					controllo = true;
				}
			}

			conn.close();
			
			return controllo;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

}
