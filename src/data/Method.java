package data;
import java.util.ArrayList;

public class Method {

	private ArrayList<Node> nodes;
	int maxHeight;
	int minCPD;
	int induced;
	String className, signature;
	private String label;
	private int exclusiveCost, inclusiveCost;
	
	public Method(String name){
		this.setLabel(name);
		nodes = new ArrayList<Node>();
		setMaxheight(0);
		exclusiveCost = 0;
		inclusiveCost = 0;
		//setMinCPD(0);
		setInduced(0);
	}

	public int getMinCPD() {
		return minCPD;
	}
	
/*	
	public int getInvocationCount(){
		 return count;
	}
	public void addInvocationCount(int c){
		 count += c;
	}*/
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void addNodes(Node node) {
		nodes.add(node);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getInduced(){
		return induced;
	}
	public void setInduced(int induced) {
		this.induced= induced;
	}

	public void setMinCPD(int minCPD) {
		this.minCPD= minCPD;
	}

	public int getMaxHeight(){
		return maxHeight;
	}
	public void setMaxheight(int maxHeight) {
		this.maxHeight= maxHeight;
	}

	public int getExclusiveCost() {
		return exclusiveCost;
	}

	public void addExclusiveCost(int exclusiveCost) {
		this.exclusiveCost += exclusiveCost;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String label) {
		this.className = label;
	}
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getInclusiveCost() {
		return inclusiveCost;
	}

	public void addInclusiveCost(int cost) {
		this.inclusiveCost += cost;
	}
/*	public void setPerCallCost(int Cost) {
		// TODO Auto-generated method stub
		this.percallCost = Cost;
		
	}

	public Integer getPerCallCost() {
		// TODO Auto-generated method stub
		return this.percallCost;
	}*/

}
