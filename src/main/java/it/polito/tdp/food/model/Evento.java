package it.polito.tdp.food.model;

public class Evento implements Comparable<Evento>{
	private Integer idEvento;
	private Integer piattaformaID;
	private Food food;
	/**
	 * @param idEvento
	 * @param piattaformaID
	 * @param food
	 */
	public Evento(Integer idEvento, Integer piattaformaID, Food food) {
		super();
		this.idEvento = idEvento;
		this.piattaformaID = piattaformaID;
		this.food = food;
	}
	
	/**
	 * @return the idEvento
	 */
	public Integer getIdEvento() {
		return idEvento;
	}
	

	/**
	 * @return the food
	 */
	public Food getFood() {
		return food;
	}
	

	/**
	 * @return the piattaformaID
	 */
	public Integer getPiattaformaID() {
		return piattaformaID;
	}

	@Override
	public int compareTo(Evento o) {
		return this.idEvento.compareTo(o.idEvento);
	}
	

}
