package data;

import java.util.ArrayList;


public class Node {


	int cost, height ,induced;
	Node adjustedParent;
	private Method method;
	private Node parent;	
	private ArrayList<Node> children;
	private ArrayList<Node> adjustedChildern;
   
/*	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}*/

/*	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}*/
/*
	public String getMethodSignature() {
		return methodSignature;
	}

	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}*/

	//private String leaf;
	//private String className;
	//private String methodName;
	//private String methodSignature;
	//private int selfTime;
	
	public Node(){
		//method= new Method();
		parent = null;
		adjustedParent= null;
		children = new ArrayList<Node>();
		adjustedChildern =new ArrayList<Node>();
		//setCost(0);
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
	public void addAdjustedChildern(Node node) {
		this.adjustedChildern.add(node);
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> childern) {
		this.children = childern;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}

/*	@Override
	public String toString() {
		return "leaf :"+this.getLeaf() + "class: "+this.className+ "methodName: "+this.getMethodName();
	}*/

	public void addChildNode(Node childNode) {
		this.children.add(childNode);
	}

/*	public void setSelfTime(int selfTime) {
		// TODO Auto-generated method stub
		this.selfTime = selfTime;
		
	}

	public int getSelfTime() {
		// TODO Auto-generated method stub
		return this.selfTime;
		
	}*/
}
