package elec_circuit;
import java.util.Random;

/**
 * 
 * @author Tsietsi Maboa
 *
 */
public class Current {
	
	private String Current_name; // from R1 to R2 or R2 to R6 
    private int number;
    private double CurrentVal;
    
    public Current(String Current_name, int number, double CurrentVal){
        this.Current_name = Current_name;
        this.number = number;
        this.CurrentVal = CurrentVal;
    }

    public double getCurrentVal() {
		return CurrentVal;
	}

	public void setCurrentVal(double currentVal) {
		CurrentVal = currentVal;
	}

	public void setCurrentName(String Current_name){
        this.Current_name = Current_name;
    }

    public String getCurrentName(){
        return Current_name;
    }

    public void setNumber(int no){
        number = no;
    }

    public int getNumber(){
        return number;
    }
}
