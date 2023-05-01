package load_shedding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;

public class Controller implements Runnable {

	@FXML
	public Rectangle vehicle_0;
	@FXML
	public Rectangle vehicle_1;
	@FXML
	public Rectangle vehicle_2;
	@FXML
	public Rectangle vehicle_3;
	@FXML
	public Rectangle vehicle_4;
	@FXML
	public Rectangle vehicle_5;
	@FXML
	public Rectangle vehicle_6;
	@FXML
	public Rectangle vehicle_7;
	
	@FXML
	public TextField txt_mine;
	@FXML
	public TextField truck_1;
	@FXML
	public TextField truck_2;
	@FXML
	public TextField truck_3;
	@FXML
	public TextField truck_4;
	@FXML
	public TextField truck_5;
	@FXML
	public TextField truck_6;
	@FXML
	public TextField truck_7;
	@FXML
	public TextField txt_space;
	
	@FXML
	public Sphere unit_0;
	@FXML
	public Sphere unit_1;
	@FXML
	public Sphere unit_2;
	@FXML
	public Sphere unit_3;
	@FXML
	public Sphere unit_4;
	@FXML
	public Sphere unit_5;
	
	@FXML
	void btn_addVertex(ActionEvent event) {
		
	}
	
	@FXML
	void btn_updateVertex(ActionEvent event) {
		
	}
	
	@FXML
	void btn_removeVertex(ActionEvent event) {
		
	}
	
	@FXML
	void btn_addEdge(ActionEvent event) {
		
	}
	
	@FXML
	void btn_updateEdge(ActionEvent event) {
		
	}
	
	@FXML
	void btn_removeEdge(ActionEvent event) {
		
	}
	
	public PowerStation power_station;
	
	void initializeValues() {
		power_station = new PowerStation();
		assert vehicle_1 != null : "fx:id=\"vehicle_1\" not found";
		assert vehicle_2 != null : "fx:id=\"vehicle_2\" not found";
		assert vehicle_3 != null : "fx:id=\"vehicle_3\" not found";
		assert vehicle_4 != null : "fx:id=\"vehicle_4\" not found";
		assert vehicle_5 != null : "fx:id=\"vehicle_5\" not found";
		assert vehicle_6 != null : "fx:id=\"vehicle_6\" not found";
		assert vehicle_7 != null : "fx:id=\"vehicle_7\" not found";
		
		moveElectricity();
	}
	
	void moveElectricity() {
		unit_0.setVisible(true);
		unit_1.setVisible(true);
		unit_2.setVisible(true);
		unit_3.setVisible(true);
		unit_4.setVisible(true);
		unit_5.setVisible(true);
		
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		double x = 120;
		double y = 200;
		
		try {
			for(int i = 0; i < 500; i++) {
				unit_0.setLayoutX(x);
				unit_1.setLayoutY(y);
				x =+ 400;
				y =+ 400;
				Thread.sleep(250);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
