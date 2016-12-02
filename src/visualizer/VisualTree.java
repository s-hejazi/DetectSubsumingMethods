package visualizer;

import java.util.ArrayList;
import java.util.List;

import ch.usi.inf.sape.trevis.model.AbstractContextTree;
import data.Method;
import data.Node;
import data.Tree;


public final class VisualTree extends AbstractContextTree {

	private final VisualTreeNode root;
	private ArrayList<Method> subsumingList;// = new ArrayList<String>();	
	public VisualTree(Tree CCT, List<Method> list) {
		Node n = CCT.getRoot();
		subsumingList = new ArrayList<Method>(list);
		root = new VisualTreeNode(n.getMethod().getLabel(), n.getCost(), "200");
		createVisualTree(CCT.getRoot(), root);
	}

	public void createVisualTree(Node n, VisualTreeNode v){
		String subsumingTree;
		for(Node c: n.getChildren()){
			subsumingTree = "200";
			if(listContains(c.getMethod())){
				subsumingTree = Integer.toString(subsumingList.indexOf(c.getMethod())*2);
			}
			else if(!v.getSubsumingSubtreeID().equals("200")){
				subsumingTree = v.getSubsumingSubtreeID();
			}
			final VisualTreeNode a = new VisualTreeNode(c.getMethod().getLabel(), c.getCost(), subsumingTree);
			v.connectChild(a);
			createVisualTree(c, a); 
		}
	}
	private boolean listContains(Method m){
		for(int i=0;i<subsumingList.size();i++){
			if(m.getLabel().equals(subsumingList.get(i).getLabel())
					&& m.getClassName().equals(subsumingList.get(i).getClassName())
					&& m.getSignature().equals(subsumingList.get(i).getSignature()))
					return true;
			
		}
		return false;
	}
	public VisualTreeNode getRoot() {
		return root;
	}

}
