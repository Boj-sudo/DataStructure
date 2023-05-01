package elec_circuit;

/**
 * 
 * @author Tsietsi Maboa
 *
 */
public class Resistor {
	private String resistor_name; // This is R1, R2, ......, (RN)
	private Object resistor_id;   // This is the ID of the resistor
	private Double resistor_value = 0.0; // A value of the resistor
	
	// Constructor
	public Resistor(String name, Object id, double value) {
		this.resistor_name = name;
		this.resistor_id = id;
		this.resistor_value = value;
	}
	
	// Getters and Setters
	public String getName() {
		return resistor_name;
	}
	
	public Object getID() {
		return resistor_id;
	}
	
	public Double getValue() {
		return resistor_value;
	}
	
	public void setName(String name) {
		this.resistor_name = name;
	}
	
	public void setID(Object id) {
		this.resistor_id = id;
	}
	
	public void setValue(Double value) {
		this.resistor_value = value;
	}
}
