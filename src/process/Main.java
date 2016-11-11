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
		
		CCTreeHandler treeHandler ;
		File folder = new File("src/profiler");
		File[] xmlFiles = folder.listFiles();
		for (File xml : xmlFiles){
			if(xml.isFile()){
				treeHandler = new CCTreeHandler();
				System.out.println(xml.getName());
				XMLParser p = new XMLParser();
			    Tree CCT = p.parse(xml);
			    
				List<Node> listOfNodes = new ArrayList<>();
				for(Method treeMethod : CCT.getMethods()){
					listOfNodes.addAll(treeMethod.getNodes());
				}
					//System.out.println("2");
					treeHandler.reduceRecursivePath(CCT.getRoot());

					//System.out.println("3");
				for(Method treeMethod : CCT.getMethods()) {					
					treeHandler.minCPD(treeMethod);
				}
					//System.out.println("4");
					treeHandler.calculateHeight(CCT.getRoot());

					//System.out.println("5");
					treeHandler.calculateInducedCost(CCT.getRoot());

				System.out.println("Total Method count: "+ CCT.getMethods().size());
				System.out.println("Subsuming method count: "+treeHandler.methodCount);
				System.out.println("Subsuming method node count: "+treeHandler.nodeCount);


			}
		}
	}


}
