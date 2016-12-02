package visualizer;

import java.util.ArrayList;
import java.util.List;

import ch.usi.inf.sape.trevis.model.AbstractContextTree;
import data.Node;
import data.Tree;


public final class VisualTree extends AbstractContextTree {

	private final VisualTreeNode root;
	private ArrayList<String> subsumingNameList;// = new ArrayList<String>();	
	public VisualTree(Tree CCT, ArrayList<String> methodList) {
		Node n = CCT.getRoot();
		subsumingNameList = new ArrayList<String>(methodList);
		root = new VisualTreeNode(n.getMethod().getLabel(), n.getCost(), "200");
		createVisualTree(CCT.getRoot(), root);
	}

	public void createVisualTree(Node n, VisualTreeNode v){
		String subsumingTree;
		for(Node c: n.getChildren()){
			subsumingTree = "100";
			if(subsumingNameList.contains(c.getMethod().getLabel())){
				subsumingTree = Integer.toString(subsumingNameList.indexOf(c.getMethod().getLabel())*2);
			}
			else if(!v.getSubsumingSubtreeID().equals("200")){
				subsumingTree = v.getSubsumingSubtreeID();
			}
			final VisualTreeNode a = new VisualTreeNode(c.getMethod().getLabel(), c.getCost(), subsumingTree);
			v.connectChild(a);
			createVisualTree(c, a); 
		}
	}
	public VisualTreeNode getRoot() {
		return root;
	}

}
