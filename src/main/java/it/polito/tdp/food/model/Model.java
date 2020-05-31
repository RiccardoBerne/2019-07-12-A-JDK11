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
	private Graph<Food, DefaultWeightedEdge> grafo;
	private List<Food> cibi;

	public Model() {
		this.dao = new FoodDao();
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

	private List<Food> bestVicini;

	public List<Food> getNeighborFood(Food food) {
		List<Food> vicini = Graphs.neighborListOf(this.grafo, food);
		List<Food> parziale = new ArrayList<>();
		bestVicini = new ArrayList<Food>();
		ricorsione(vicini, parziale, food);
		return bestVicini;

	}

	private void ricorsione(List<Food> vicini, List<Food> parziale, Food food) {
		Integer pesoP = -1;
		Integer pesoB = -1;
		for (Food f : parziale) {
			pesoP += (int) this.grafo.getEdgeWeight(this.grafo.getEdge(food, f));
		}
		if (bestVicini.size() > 0) {
			for (Food f : bestVicini) {
				pesoB += (int) this.grafo.getEdgeWeight(this.grafo.getEdge(food, f));
			}
			if (pesoP > pesoB) {
				this.bestVicini = new ArrayList<>(parziale);
				return;
			}
		}else {
			this.bestVicini= new ArrayList<>(parziale);
		}
		// Integer peso = countPeso+
		// (int)this.grafo.getEdgeWeight(this.grafo.getEdge(food, f));
		Integer countPeso = -1;
		for (Food f : vicini) {
			if (!parziale.contains(f)) {
				parziale.add(f);
				ricorsione(vicini, parziale, food);
				parziale.remove(f);
			}

		}

	}

}
