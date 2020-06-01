package it.polito.tdp.food.model;

public class Statistiche {
	private Integer numeroCibi;
	private Double totDurata;
	
	/**
	 * @param numeroCibi
	 * @param totDurata
	 */
	public Statistiche(Integer numeroCibi, Double totDurata) {
		super();
		this.numeroCibi = numeroCibi;
		this.totDurata = totDurata;
	}
	/**
	 * @return the numeroCibi
	 */
	public Integer getNumeroCibi() {
		return numeroCibi;
	}
	/**
	 * @param numeroCibi the numeroCibi to set
	 */
	public void setNumeroCibi(Integer numeroCibi) {
		this.numeroCibi = numeroCibi;
	}
	/**
	 * @return the totDurata
	 */
	public Double getTotDurata() {
		return totDurata;
	}
	/**
	 * @param totDurata the totDurata to set
	 */
	public void setTotDurata(Double totDurata) {
		this.totDurata = totDurata;
	}
	@Override
	public String toString() {
		return "Cibi Eseguiti: " + numeroCibi + ", Durata Totale: " + totDurata ;
	}
	

}
