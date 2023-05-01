package elec_circuit;

/**
 * 
 * @author Tsietsi Maboa
 *
 */
public class NodalPath {
	String path_name;
	
	/**
	 * @param path_name
	 */
	public NodalPath(String pathName) {
		this.path_name = pathName;
	}
	
	/**
	 * @return
	 */
	public String getPath() {
		return path_name;
	}
	
	/**
	 * @param pathName
	 */
	public void setPath(String pathName) {
		this.path_name = pathName;
	}
}
