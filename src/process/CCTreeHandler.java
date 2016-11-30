package process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import data.Method;
import data.Node;

public class CCTreeHandler {

	public int subsumingMethodCount = 0;
	public int SubsumingNodeCount = 0;
	private static int thresholdCPD = 4;
	private ArrayList<Method> subsumingMethods = new ArrayList<>();

	public void reduceRecursivePath(Node node) {

		node.setAdjustedParent(findAdjustedParent(node));
		if (node.getAdjustedParent() != null)
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
				// m = n.getMethod();
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
			if (isSubsumedMethod(c.getMethod())) {
				n.setInduced(n.getInduced() + c.getInduced());
			}
		}
		n.getMethod().setInduced(n.getMethod().getInduced() + n.getInduced());
	}

	private boolean isSubsumedMethod(Method m) {
		/// height and minimum common parent distance
		/// greater than four as subsuming methods. others, subsumed
		/// check
		if (m.getMinCPD() > thresholdCPD && m.getMaxHeight() > thresholdCPD) {
			if (!subsumingMethods.contains(m)) {
				subsumingMethods.add(m);
				subsumingMethodCount++;
				SubsumingNodeCount += m.getNodes().size();
			}
			return false;
		} else // is subsumed
			return true;
	}

	/*
	 * public int calculateCountNodeSelfTime(int methodCountValue, List<Node>
	 * listOfNodes) { int exclusiveCost = 0; for(Node currentNode :
	 * listOfNodes){ int nodeSelfTime = currentNode.getSelfTime(); exclusiveCost
	 * += nodeSelfTime * methodCountValue; } return exclusiveCost;
	 * 
	 * }
	 */

	public void rankTopTenMethodsByExclusiveCost(ArrayList<Method> methods) {
		Map<String, Integer> methodCost = new HashMap<String, Integer>();
		String typeOfMethod = "Hot Methods";
		if(methods.size() > 0){
			printMethodName(methods, methodCost, typeOfMethod);
	
		}else{
			System.out.println("No hot methods");
		}
		System.out.println("------------------------------------------------------");
	}

	/*private void printHotMethodName(ArrayList<Method> methods, Map<String, Integer> methodCost, String typeOfMethod) {
		for (int i = 0; i < methods.size(); i++)
			methodCost.put(methods.get(i).getLabel(), methods.get(i).getExclusiveCost());

		List<Map.Entry<String, Integer>> list = mapSort(methodCost);
		
		System.out.println(typeOfMethod + ":");
		for (int i = 0; i < 10; i++)
			System.out.println(list.get(i).getKey());// +", cost "+
														// list.get(i).getValue());
	}*/

	public void rankTopSubsumingMethods() {
		Map<String, Integer> methodCost = new HashMap<>();
		String typeOfMethod = "Subsuming Methods";
		if (subsumingMethods.size() > 0) {
			printMethodName(subsumingMethods, methodCost, typeOfMethod);

		} else {
			System.out.println("No subsuming methods");
		}
		System.out.println("------------------------------------------------------");

	}

	private void printMethodName(ArrayList<Method> methods, Map<String, Integer> methodCost, String typeOfMethod) {
		for (int i = 0; i < methods.size(); i++)
			methodCost.put(methods.get(i).getLabel(), methods.get(i).getInduced());

		List<Map.Entry<String, Integer>> list = mapSort(methodCost);
		System.out.println(typeOfMethod + " :");
		for (int i = 0; i < 10 && i < list.size(); i++) {			
				System.out.println(list.get(i).getKey());// +", cost "+
															// list.get(i).getValue());
		}
	}

	public List<Map.Entry<String, Integer>> mapSort(Map<String, Integer> methodCost) {
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(methodCost.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
				return (m1.getValue().compareTo(m2.getValue()));
			}
		});
		Collections.reverse(list);
		return list;
	}
}
