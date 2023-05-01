package load_shedding;

public class Electricity {

	private String num_voltage;
	private double electricity_units;
	private int num_households;
	
	public Electricity(String volts, double amount, int number) {
		this.num_voltage = volts;
		this.electricity_units = amount;
		this.num_households = number; 
	}
	
	public String getVoltage() {
		return num_voltage;
	}
	
	public double getUnits() {
		return electricity_units;
	}
	
	public int getHouseholds() {
		return num_households;
	}
	
	public void setVoltage(String volts) {
		this.num_voltage = volts;
	}
	
	public void setUnits(double amount) {
		this.electricity_units = amount;
	}
	
	public void setHouseholds(int number) {
		this.num_households = number;
	}
}
