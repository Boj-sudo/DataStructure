package elec_circuit;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;

/**
 * 
 * @author Tsietsi Maboa
 *
 */
public class Controller implements Runnable {
	
	@FXML
	private TextArea txt_path;
	@FXML
	private ListView<String> current_list;
	
	@FXML
	public Ellipse vol_total;
	
	@FXML
	public Rectangle resistor_0;
	@FXML
	public Rectangle resistor_1;
	@FXML
	public Rectangle resistor_2;
	@FXML
	public Rectangle resistor_3;
	@FXML
	public Rectangle resistor_4;
	@FXML
	public Rectangle resistor_5;
	@FXML
	public Rectangle resistor_6;
	@FXML
	public Rectangle resistor_7;
	@FXML
	public Rectangle resistor_8;
	@FXML
	public Rectangle resistor_9;
	
	@FXML
	public TextField txt_resistor_0;
	@FXML
	public TextField txt_resistor_1;
	@FXML
	public TextField txt_resistor_2;
	@FXML
	public TextField txt_resistor_3;
	@FXML
	public TextField txt_resistor_4;
	@FXML
	public TextField txt_resistor_5;
	@FXML
	public TextField txt_resistor_6;
	@FXML
	public TextField txt_resistor_7;
	@FXML
	public TextField txt_resistor_8;
	@FXML
	public TextField txt_resistor_9;
	@FXML
	public TextField txt_voltage;
	@FXML
	public TextField txt_ground;
	
	@FXML
	public Sphere current_1;
	@FXML
	public Sphere current_2;
	@FXML
	public Sphere current_3;
	@FXML
	public Sphere current_4;
	@FXML
	public Sphere current_5;
	@FXML
	public Sphere current_6;
	@FXML
	public Sphere current_7;
	@FXML
	public Sphere current_8;
	
	public Circuit circuit;
	void initializeVars() {
		circuit = new Circuit();
		
		assert resistor_0 != null : "fx:id=\"resistor_0\" not found";
		assert resistor_1 != null : "fx:id=\"resistor_1\" not found";
		assert resistor_2 != null : "fx:id=\"resistor_2\" not found";
		assert resistor_3 != null : "fx:id=\"resistor_3\" not found";
		assert resistor_4 != null : "fx:id=\"resistor_4\" not found";
		assert resistor_5 != null : "fx:id=\"resistor_5\" not found";
		assert resistor_6 != null : "fx:id=\"resistor_6\" not found";
		assert resistor_7 != null : "fx:id=\"resistor_7\" not found";
		assert resistor_8 != null : "fx:id=\"resistor_8\" not found";
		assert resistor_9 != null : "fx:id=\"resistor_9\" not found";
		
		moveCurrent();
	}
	
	@FXML
	private void addResistor(ActionEvent event) {
		
	}
	
	@FXML
	public void addCurrent(ActionEvent event) {
		
	}
	
	void moveCurrent() {
		current_1.setVisible(true);
		current_2.setVisible(true);
		current_3.setVisible(true);
		current_4.setVisible(true);
		current_5.setVisible(true);
		current_6.setVisible(true);
		current_7.setVisible(true);
		current_8.setVisible(true);
		
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		double x = 123;
		double y = 212;
		
		try {
			for(int i = 0; i < 600; i++) {
				current_1.setLayoutX(x);
				current_2.setLayoutY(y);
				
				y =+ 500;
				x =+ 500;
				
				Thread.sleep(250);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
