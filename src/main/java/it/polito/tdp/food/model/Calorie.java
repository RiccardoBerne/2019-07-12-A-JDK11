package it.polito.tdp.food.model;

public class Calorie implements Comparable<Calorie> {
	private Food food;
	private Double calorie;

	/**
	 * @param food
	 * @param calorie
	 */
	public Calorie(Food food, Double calorie) {
		super();
		this.food = food;
		this.calorie = calorie;
	}

	/**
	 * @return the food
	 */
	public Food getFood() {
		return food;
	}

	/**
	 * @param food the food to set
	 */
	public void setFood(Food food) {
		this.food = food;
	}

	/**
	 * @return the calorie
	 */
	public Double getCalorie() {
		return calorie;
	}

	/**
	 * @param calorie the calorie to set
	 */
	public void setCalorie(Double calorie) {
		this.calorie = calorie;
	}

	@Override
	public int compareTo(Calorie o) {
		return -this.calorie.compareTo(o.calorie);
	}

}
