package it.polito.tdp.food.model;

public class Food implements Comparable<Food>{
	private Integer food_code;
	private String display_name;
	private Integer porzioni;
	
	/**
	 * @param food_code
	 */
	public Food(Integer food_code) {
		super();
		this.food_code = food_code;
	}
	/**
	 * 
	 * @param food_code
	 * @param display_name
	 * @param porzioni
	 */
	public Food(Integer food_code, String display_name, Integer porzioni) {
		super();
		this.food_code = food_code;
		this.display_name = display_name;
		this.porzioni = porzioni;
	}
	/**
	 * @return the food_code
	 */
	public Integer getFood_code() {
		return food_code;
	}
	/**
	 * @param food_code the food_code to set
	 */
	public void setFood_code(Integer food_code) {
		this.food_code = food_code;
	}
	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}
	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @return the porzioni
	 */
	public Integer getPorzioni() {
		return porzioni;
	}
	/**
	 * @param porzioni the porzioni to set
	 */
	public void setPorzioni(Integer porzioni) {
		this.porzioni = porzioni;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food_code == null) ? 0 : food_code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (food_code == null) {
			if (other.food_code != null)
				return false;
		} else if (!food_code.equals(other.food_code))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  display_name;
	}
	@Override
	public int compareTo(Food o) {
		return this.display_name.compareTo(o.getDisplay_name());
	}
	
	
	
	
}
