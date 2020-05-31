package it.polito.tdp.food.model;

public class Adiacenza {
	private Food cod1;
	private Food cod2;
	private Integer CalMedie;
	/**
	 * @param cod1
	 * @param cod2
	 * @param calMedie
	 */
	public Adiacenza(Food cod1, Food cod2, Integer calMedie) {
		super();
		this.cod1 = cod1;
		this.cod2 = cod2;
		CalMedie = calMedie;
	}
	/**
	 * @return the cod1
	 */
	public Food getCod1() {
		return cod1;
	}
	/**
	 * @param cod1 the cod1 to set
	 */
	public void setCod1(Food cod1) {
		this.cod1 = cod1;
	}
	/**
	 * @return the cod2
	 */
	public Food getCod2() {
		return cod2;
	}
	/**
	 * @param cod2 the cod2 to set
	 */
	public void setCod2(Food cod2) {
		this.cod2 = cod2;
	}
	/**
	 * @return the calMedie
	 */
	public Integer getCalMedie() {
		return CalMedie;
	}
	/**
	 * @param calMedie the calMedie to set
	 */
	public void setCalMedie(Integer calMedie) {
		CalMedie = calMedie;
	}

}
