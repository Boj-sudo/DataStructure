package load_shedding;

public class NodePath {
	
	String path_name;
	
	public NodePath(String name) {
		this.path_name = name;
	}
	
	public String getPath() {
		return path_name;
	}
	
	public void setPath(String name) {
		this.path_name = name;
	}
}
