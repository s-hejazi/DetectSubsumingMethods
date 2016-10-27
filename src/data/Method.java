package data;

import java.util.ArrayList;

public class Method {

	private ArrayList<Node> nodes;
	int maxHeight,minCPD,induced;
	private String label;
	public Method(String name){
		this.setLabel(name);
		nodes = new ArrayList<Node>();
		setMaxheight(0);
		setMinCPD(0);
		setInduced(0);
	}
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	public void addNodes(Node node) {
		nodes.add(node);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setInduced(int induced) {
		this.induced= induced;
		
	}
	public void setMinCPD(int minCPD) {
		this.minCPD= minCPD;
		
	}
	public void setMaxheight(int maxHeight) {
		this.maxHeight= maxHeight;
	}
}
