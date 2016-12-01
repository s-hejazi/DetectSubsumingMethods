package visualizer;

import ch.usi.inf.sape.trevis.model.AbstractContextTreeNode;


public final class VisualTreeNode extends AbstractContextTreeNode {
	private final long weight;	
	private boolean isSubsumingSubtree = false;	
	public long getWeight() {
		return weight;
	}
	public VisualTreeNode(final String label, long weight, boolean subtree) {
		super(label);
		this.weight = weight;
		this.isSubsumingSubtree = subtree;
	}
	public boolean isInSubsumingSubtree() {
		
		return isSubsumingSubtree;
	}
		
}
