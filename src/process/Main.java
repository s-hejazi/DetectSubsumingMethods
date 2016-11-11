package process;

import data.Method;
import data.Node;
import data.Tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CCTreeHandler treeHandler = new CCTreeHandler();
		File folder = new File("src/profiler");
		File[] xmlFiles = folder.listFiles();
		for (File xml : xmlFiles){
			if(xml.isFile()){
				System.out.println(xml.getName());
				XMLParser p = new XMLParser();
			p.parse(xml);

		/*	Tree ccTree = p.parse(xml);
				List<Node> listOfNodes = new ArrayList<>();
				for(Method treeMethod : ccTree.getMethods()){
					listOfNodes.addAll(treeMethod.getNodes());
				}
				for(Node node : listOfNodes){
					treeHandler.reduceRecursivePath(node);
				}
				for(Method treeMethod : ccTree.getMethods()) {
					treeHandler.minCPD(treeMethod);
				}*/
				//reduce recursive path
				//calculate minCPD
				//calculate height
				//for( method with height & CPD >4)
				//calculate induced cost
				//store result


			}
		}
	}


}
