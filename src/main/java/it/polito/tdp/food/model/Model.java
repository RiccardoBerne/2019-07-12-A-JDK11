package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private FoodDao dao;
	private Simulatore sim;
	private Graph<Food, DefaultWeightedEdge> grafo;
	private List<Food> cibi;

	public Model() {
		this.dao = new FoodDao();
		this.sim = new Simulatore();
	}

	public void creaGrafo(Integer porzione) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.cibi = new ArrayList<>(this.dao.listFoodPorzioni(porzione));
		for (Food f : cibi) {
			if (!this.grafo.containsVertex(f)) {
				this.grafo.addVertex(f);
			}
		}
		System.out.println(this.grafo.vertexSet().size() + "\n");
		for (Adiacenza a : this.dao.getAdiacenze()) {
			if (this.grafo.containsVertex(a.getCod1()) && this.grafo.containsVertex(a.getCod2())) {
				if (!this.grafo.containsEdge(a.getCod1(), a.getCod2()) && a.getCalMedie() > 0) {
					Graphs.addEdge(this.grafo, a.getCod1(), a.getCod2(), a.getCalMedie());
				}
			}
		}
		System.out.println(this.grafo.edgeSet().size() + "\n");
		
	}
	public List<Food> getListFood() {
		Collections.sort(this.cibi);
		return this.cibi;
	}
	
	
	public List<Calorie> getNeighborFood(Food food) {
		List<Calorie> bestVicini= new ArrayList<>();
		List<Food> vicini = Graphs.neighborListOf(this.grafo, food);
		for(Food v: vicini) {
			Calorie c= new Calorie(v,this.grafo.getEdgeWeight(this.grafo.getEdge(food, v)));
			bestVicini.add(c);
		}
		Collections.sort(bestVicini);
		return bestVicini;

	}

	public void simula(Food food, Integer k) {
		this.sim.inizializzazione(food, this.grafo, k);
		this.sim.run();
		
	}
	public Statistiche getStats() {
		return this.sim.getStatistiche();
	}
	
}
