/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Calorie;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtPorzioni"
	private TextField txtPorzioni; // Value injected by FXMLLoader

	@FXML // fx:id="txtK"
	private TextField txtK; // Value injected by FXMLLoader

	@FXML // fx:id="btnAnalisi"
	private Button btnAnalisi; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalorie"
	private Button btnCalorie; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimula"
	private Button btnSimula; // Value injected by FXMLLoader

	@FXML // fx:id="boxFood"
	private ComboBox<Food> boxFood; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCreaGrafo(ActionEvent event) {
		txtResult.clear();
		Integer porzione;
		try {
			porzione = Integer.parseInt(this.txtPorzioni.getText());
		} catch (NumberFormatException e) {
			this.txtResult.setText("Non hai selezionato un numero valido");
			return;
		}
		if (porzione < 1 && porzione > 3) {
			this.txtResult.setText("Non hai selezionato nel range appropriato");
			return;
		}
		this.model.creaGrafo(porzione);
		this.boxFood.getItems().addAll(this.model.getListFood());

	}

	@FXML
	void doCalorie(ActionEvent event) {
		txtResult.clear();
		Food food = this.boxFood.getValue();
		List<Calorie> bestVicini = new ArrayList<>(this.model.getNeighborFood(food));
		Integer count = 0;
		for (Calorie c : bestVicini) {
			if (count < 5) {
				this.txtResult.appendText(c.getFood().getDisplay_name() + ", Calorie Medie: " + c.getCalorie() + "\n");
				count++;
			} else {
				break;
			}

		}
	}

	@FXML
	void doSimula(ActionEvent event) {
		txtResult.clear();
		Food food = this.boxFood.getValue();
		Integer k;
		try {
			k = Integer.parseInt(this.txtK.getText());
		} catch (NumberFormatException e) {
			this.txtResult.appendText("K non è nel formato giusto");
			return;
		}
		this.model.simula(food, k);
		this.txtResult.appendText(this.model.getStats().toString());
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
		assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
		assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
		assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
		assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
