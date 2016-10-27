package data;

import java.util.ArrayList;


public class Node {

	private Method method;
	int cost, height ,induced;
	private Node parent;
	Node adjustedParent;
	private ArrayList<Node> childern;
	ArrayList<Node> adjustedChildern;
	
	public Node(){
		//method= new Method();
		parent = null;
		adjustedParent= null;
		//childern = new ArrayList<Node>();
		//adjustedChildern =new ArrayList<Node>();
		setCost(0);
		setHeight(0);
		setInduced(0);
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getInduced() {
		return induced;
	}

	public void setInduced(int induced) {
		this.induced = induced;
	}

	public Node getAdjustedParent() {
		return adjustedParent;
	}

	public void setAdjustedParent(Node adjustedParent) {
		this.adjustedParent = adjustedParent;
	}

	public ArrayList<Node> getAdjustedChildern() {
		return adjustedChildern;
	}

	public void setAdjustedChildern(ArrayList<Node> adjustedChildern) {
		this.adjustedChildern = adjustedChildern;
	}
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildern() {
		return childern;
	}
	public void setChildern(ArrayList<Node> childern) {
		this.childern = childern;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
}
