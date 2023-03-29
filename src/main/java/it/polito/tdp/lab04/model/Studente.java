package it.polito.tdp.lab04.model;

public class Studente {
	
		private String matricola;
		private String cognome;
		private String nome;
		private String CDS;
		
		public Studente(String matricola, String cognome, String nome, String cDS) {
			
			this.matricola = matricola;
			this.cognome = cognome;
			this.nome = nome;
			CDS = cDS;
		}

		public String getMatricola() {
			return matricola;
		}

		public String getCognome() {
			return cognome;
		}

		public String getNome() {
			return nome;
		}

		public String getCDS() {
			return CDS;
		}

		@Override
		public String toString() {
			return "Matricola: " + matricola + ", Cognome: " + cognome + ", Nome: " + nome ;
		}

		
		
		
	
}
