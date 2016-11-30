package process;

import data.Method;
import data.Node;
import data.Tree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CCTreeHandler treeHandler ;
		//List<Tree> cctTreeList = new ArrayList<Tree>();
		File folder = new File("src/profiler");
		File[] xmlFiles = folder.listFiles();
		for (File xml : xmlFiles){
			if(xml.isFile()){				
				System.out.println(xml.getName());
				XMLParser p = new XMLParser();
			    Tree CCT = p.parse(xml);
/*			    cctTreeList.add(CCT);
			}	    
		}
		
		for(Tree CCT : cctTreeList){	*/	    	
			    		
			treeHandler = new CCTreeHandler();			
			treeHandler.reduceRecursivePath(CCT.getRoot());
			
			for(Method treeMethod : CCT.getMethods()) {	
				treeHandler.minCPD(treeMethod);
					}			
			treeHandler.calculateHeight(CCT.getRoot());
			treeHandler.calculateInducedCost(CCT.getRoot());

			System.out.println("Total Method count: "+ CCT.getMethods().size());
			System.out.println("Subsuming method count: "+treeHandler.subsumingMethodCount);
			System.out.println("Subsuming method node count: "+treeHandler.SubsumingNodeCount);
			
			treeHandler.rankTopTenMethodsByExclusiveCost(CCT.getMethods());
			treeHandler.rankTopSubsumingMethods();
			
		}
	}

	}
}
