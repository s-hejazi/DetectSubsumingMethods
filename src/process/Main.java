package process;

import java.io.File;

import data.Method;
import data.Tree;
import visualizer.VisualizeMain;

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
			    CCT.setTitle(xml.getName());
   	
  		
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
			
			//////////treeHandler.rankTopTenMethodsByExclusiveCost(CCT.getMethods());
			treeHandler.rankTopSubsumingMethods();
			//////////treeHandler.rankTopTenMethodsByInclusiveCost(CCT.getMethods());
			if(treeHandler.subsumingMethodCount>10)
				VisualizeMain.createTreeView(CCT, treeHandler.subsumingMethods.subList(0, 10));
			else
				VisualizeMain.createTreeView(CCT, treeHandler.subsumingMethods);
			
		}
	}

	}
}
