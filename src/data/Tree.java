package data;

import java.util.ArrayList;

public class Tree {

	Node root;
	ArrayList<Method> methods;
	String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}

	public Tree(Node root, ArrayList<Method> methodList){
		this.root = root;
		this.methods = methodList;
	}
}
