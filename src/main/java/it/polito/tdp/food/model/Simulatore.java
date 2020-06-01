package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	// Parametri
	private Graph<Food, DefaultWeightedEdge> NewGraph;
	private Integer piattaformeCucina;
	// Output
	private Integer numeroCibi;
	private Double totDurata;
	// Coda
	private PriorityQueue<Evento> coda;
	// Stato del modello
	private List<Food> cibiEseguiti;

	public void inizializzazione(Food sorgente, Graph<Food, DefaultWeightedEdge> grafo, Integer k) {
		this.NewGraph = grafo;
		this.piattaformeCucina = k;

		this.coda = new PriorityQueue<Evento>();

		this.numeroCibi = 0;
		this.totDurata = 0.0;

		this.cibiEseguiti = new ArrayList<Food>();

		// Inizializzazione primo evento nella prima piattaforma
		Evento e = new Evento(1, 1, sorgente);
		this.cibiEseguiti.add(sorgente);
		this.coda.add(e);

	}

	public void run() {
		while (!this.coda.isEmpty()) {
			Evento e = this.coda.poll();
			processEvent(e);
		}

	}

	private void processEvent(Evento e) {
		// Raccolta dei migliori vicini
		List<Calorie> bestVicini = new ArrayList<Calorie>();
		List<Food> vicini = Graphs.neighborListOf(this.NewGraph, e.getFood());
		for (Food f : vicini) {
			Calorie c = new Calorie(f, this.NewGraph.getEdgeWeight(this.NewGraph.getEdge(e.getFood(), f)));
			bestVicini.add(c);
		}
		Collections.sort(bestVicini);
		// Processamento dell'evento
		if (e.getIdEvento() == 1) {
			Integer piattaforma = 2;
			Integer idEvento = 1;
			for (Calorie c : bestVicini) {
				if (piattaforma <= this.piattaformeCucina) {
					if(!this.cibiEseguiti.contains(c.getFood())) {
						// Aggiunta nuovo Evento
						Evento NEWe = new Evento(e.getIdEvento() + idEvento, piattaforma, c.getFood());
						this.cibiEseguiti.add(c.getFood());
						this.coda.add(NEWe);
						// Aggiornamento statistiche
						this.numeroCibi++;
						this.totDurata += c.getCalorie();
						// Aggiornamento variabili
						idEvento++;
						piattaforma++;
					} 
				}else {
					break;
				}
			}
		} else {
			Calorie best = bestVicini.get(0);
			if(!this.cibiEseguiti.contains(best.getFood())) {
				//Aggiunta nuovo evento
				Evento NEWe = new Evento(e.getIdEvento()+1, e.getPiattaformaID(), best.getFood());
				this.cibiEseguiti.add(best.getFood());
				this.coda.add(NEWe);
				//Aggiornamento Statistiche
				this.numeroCibi++;
				this.totDurata += best.getCalorie();
			}
		}

	}
	public Statistiche getStatistiche() {
		Statistiche stats = new Statistiche(this.numeroCibi, this.totDurata);
		return stats;
	}

}
