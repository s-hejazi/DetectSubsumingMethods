package visualizer;

import ch.usi.inf.sape.trevis.model.AbstractContextTreeNode;


public final class VisualTreeNode extends AbstractContextTreeNode {
	private final long weight;	
	private String SubsumingSubtreeID ;	
	public long getWeight() {
		return weight;
	}
	public VisualTreeNode(final String label, long weight, String subtree) {
		super(label);
		this.weight = weight;
		this.SubsumingSubtreeID = subtree;
	}
	public String getSubsumingSubtreeID() {
		
		return SubsumingSubtreeID;
	}
		
}
