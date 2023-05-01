package load_shedding;

public class Vehicles {

	/**
	 * Attributes
	 */
	private String vehicleName;
	private Object numPlate;
	private Double numVehicle;
	
	/**
	 * Constructor
	 * @param vehicle_name
	 * @param num_plate
	 * @param num_vehicle
	 */
	public Vehicles(String vehicle_name, Object num_plate, double num_vehicle) {
		this.vehicleName = vehicle_name;
		this.numPlate = num_plate;
		this.numVehicle = num_vehicle;
	}
	
	// Getters and Setters
	public String getName() {
		return vehicleName;
	}
	
	public Object getPlate() {
		return numPlate;
	}
	
	public Double getVehicle() {
		return numVehicle;
	}
	
	public void setName(String vehicle_name) {
		this.vehicleName = vehicle_name;
	}
	
	public void setPlate(Object num_plate) {
		this.numPlate = num_plate;
	}
	
	public void setVehicle(Double num_vehicle) {
		this.numVehicle = num_vehicle;
	}
}
