package data;

import java.util.ArrayList;

public class Tree {

	Node root;
	ArrayList<Method> methods;
	public Tree(Node root, ArrayList<Method> methodList){
		this.root = root;
		this.methods = methodList;
	}
}
