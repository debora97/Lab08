package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	public Graph<String, DefaultEdge> grafo;
	private List<String> parole;
	

	public void createGraph(int numeroLettere) {
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		WordDAO dao = new WordDAO();
		this.parole=dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(this.grafo, this.parole);
		for(String s: this.grafo.vertexSet()) {
			// faccio una lista con tutte le parole di quella lunghezza che differiscono di una lettera
			List<String> paroleSimili= this.connettiParole(s, parole);
			
			//faccio il for per aggiungere tutti gli arichi a quella parola (lista che ho fatto prima)
			for(String parola: paroleSimili) {
				this.grafo.addEdge(s, parola);
			}
		}

		System.err.println("createGraph -- TODO");
	}

	private List<String> connettiParole(String s, List<String> parole2) {
		List<String > paroleConnesse= new ArrayList<String>();
		for(String prova: parole2) {
			if(this.isSimile(s,prova)) {
				paroleConnesse.add(prova);
			}
		}
		
		return paroleConnesse;
	}

	private boolean isSimile(String s, String prova) {
		int cont=0;
		for(int i =0; i<s.length(); i++) {
			char l1=s.charAt(i);
			char l2=prova.charAt(i);
		if(l1!=l2) {
			cont++;
		}
		}
		if(cont==1)
		return true;
		else return false;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> risultati= new ArrayList();
				risultati.addAll(Graphs.neighborListOf(this.grafo, parolaInserita));

		System.err.println("displayNeighbours -- TODO");
		return risultati;
	}
	
	public String findMaxWord() {
		int max=0;
		int cont=0;
		String parola=null;
		for(String s: this.grafo.vertexSet()) {
				cont=	this.grafo.degreeOf(s);
			if(cont>=max) {
			max=cont;
			parola=s;
		}	
				

		}
		
		System.err.println("findMaxDegree -- TODO");
		return parola;
		
	}

	public int findMaxDegree() {
		int max=0;
		int cont=0;
		
		for(String s: this.grafo.vertexSet()) {
				cont=	this.grafo.degreeOf(s);
			if(cont>=max) {
			max=cont;
			
		}	
				

		}
		
		System.err.println("findMaxDegree -- TODO");
		return max;
		
	}
}
