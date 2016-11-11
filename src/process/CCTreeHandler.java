package process;

import data.Method;
import data.Node;

public class CCTreeHandler {

	public int methodCount = 0;
	public int nodeCount = 0;

	public void reduceRecursivePath(Node node) {

		node.setAdjustedParent(findAdjustedParent(node));
		if(node.getAdjustedParent() != null)
		node.getAdjustedParent().addAdjustedChildern(node);

		for (Node childNode : node.getChildren()) {
			reduceRecursivePath(childNode);
		}
		}
	

	public Node findAdjustedParent(Node currentNode) {
		Node match1 = null;
		Node match2 = null;
		Node currentParent = currentNode.getParent();
		while (currentParent != null && match2 == null) {
			// Check in paper if method or method name should be compared
			if (currentParent.getMethod() == currentNode.getMethod()) {
				if (match1 == null) {
					match1 = currentParent;
				} else {
					match2 = currentParent;
				}
			}
				currentParent = currentParent.getAdjustedParent();
			
		}

		if (match1 == null || match2 == null) {
			return currentNode.getParent();
		}
		Node node1 = currentNode.getParent();
		Node node2 = match1.getAdjustedParent();

		while (node1 != match1 && node2 != match2) {
			if (node1.getMethod() != node2.getMethod()) {
				return currentNode.getParent();
			}
			node1 = node1.getAdjustedParent();
			node2 = node2.getAdjustedParent();
		}
		if (node1 != match1 || node2 != match2) {
			return currentParent;
		}
		return match1.getAdjustedParent();
	}

	public void minCPD(Method m) {
		m.setMinCPD(Integer.MAX_VALUE);
		Node n = m.getNodes().get(0);
		n = n.getAdjustedParent();
		while (n != null) {
			int dist = CPD(n.getMethod(), m);
			if (dist < m.getMinCPD()) {
				m.setMinCPD(dist);
				//m = n.getMethod();
			}
			n = n.getParent();
		}
	}

	private int CPD(Method p, Method m) {
		int cpd = 0;
		for (Node n : m.getNodes()) {
			int dist = distance(p, n);
			if (dist > cpd) {
				cpd = dist;
			}
		}
		return cpd;
	}

	private int distance(Method m, Node n) {
		int dist = 0;
		while (true) {
			++dist;
			n = n.getAdjustedParent();
			if (n == null) {
				return Integer.MAX_VALUE;
			}
			if (n.getMethod() == m) {
				return dist;
			}
		}

	}

	public void calculateHeight(Node v) {
		v.setHeight(0);
		for (Node c : v.getAdjustedChildern()) {
			calculateHeight(c);
			if (c.getHeight() + 1 > v.getHeight()) {
				v.setHeight(c.getHeight() + 1);
			}
		}
		if (v.getHeight() > v.getMethod().getMaxHeight()) {
			v.getMethod().setMaxheight(v.getHeight());
		}
	}

	public void calculateInducedCost(Node n) {
		n.setInduced(n.getCost());
		for (Node c : n.getChildren()) {
			calculateInducedCost(c);
			if (isSubsumedMethod(c)) {
				n.setInduced(n.getInduced() + c.getInduced());
			}
		}
		n.getMethod().setInduced(n.getMethod().getInduced() + n.getInduced());
	}

	private boolean isSubsumedMethod(Node node) {
		///height and minimum common parent distance
		///greater than four as subsuming methods. others, subsumed 
		///check
		if (node.getMethod().getMinCPD() > 4 && node.getHeight() > 4){
			methodCount++;
			nodeCount += node.getMethod().getNodes().size();
			return false;
		}
		else //is subsumed
		return true;
	}
}
