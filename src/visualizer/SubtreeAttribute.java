package visualizer;

import ch.usi.inf.sape.trevis.model.ContextTreeNode;
import ch.usi.inf.sape.trevis.model.attribute.BooleanAttribute;

public class SubtreeAttribute extends BooleanAttribute {
	public String getName() {
		return "Subsuming tree";
	}
	public String getDescription() {
		return "Represents the subsuming subtree";
	}
	public boolean evaluate(ContextTreeNode node) {
		return ((VisualTreeNode)node).isInSubsumingSubtree();
	}
}