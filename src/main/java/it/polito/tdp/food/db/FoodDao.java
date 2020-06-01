package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listFoodPorzioni(Integer porzione){
		String sql = "SELECT food.food_code as cod, food.display_name as name, COUNT(DISTINCT(portion.portion_id)) AS porzioni " + 
				"FROM food, `portion` " + 
				"WHERE food.food_code=portion.food_code " + 
				"GROUP BY food.food_code "+ 
				"HAVING porzioni=?" ;
		List<Food> cibi = new ArrayList<Food>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, porzione);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					cibi.add(new Food(res.getInt("cod"),res.getString("name"), res.getInt("porzioni")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return cibi ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}

	public List<Adiacenza> getAdiacenze(){
			String sql = "SELECT fc1.food_code as cod1, f1.display_name as nome1, fc2.food_code as cod2, f2.display_name as nome2, AVG(c1.condiment_calories) AS calMedie " + 
					"FROM food_condiment AS fc1, food_condiment AS fc2, condiment AS c1, condiment AS c2, food AS f1, food AS f2 " + 
					"WHERE f1.food_code=fc1.food_code AND f2.food_code=fc2.food_code " + 
					"AND fc1.food_code>fc2.food_code AND c1.condiment_code=c2.condiment_code AND " + 
					"fc1.condiment_code=c1.condiment_code AND fc2.condiment_code=c2.condiment_code " + 
					"GROUP BY fc1.food_code, fc2.food_code " ;
			List<Adiacenza> adiacenze = new ArrayList<Adiacenza>();
			try {
				Connection conn = DBConnect.getConnection() ;

				PreparedStatement st = conn.prepareStatement(sql) ;
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						Food f1= new Food(res.getInt("cod1"), res.getString("nome1"));
						Food f2 = new Food(res.getInt("cod2"),res.getString("nome2"));
						Adiacenza a = new Adiacenza(f1, f2, res.getInt("calMedie"));
						adiacenze.add(a);
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
				
				conn.close();
				return adiacenze ;

			} catch (SQLException e) {
				e.printStackTrace();
				return null ;
			}

		}
	

}
