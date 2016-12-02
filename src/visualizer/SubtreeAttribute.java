package visualizer;

import ch.usi.inf.sape.trevis.model.ContextTreeNode;
import ch.usi.inf.sape.trevis.model.attribute.BooleanAttribute;
import ch.usi.inf.sape.trevis.model.attribute.StringAttribute;

public class SubtreeAttribute extends StringAttribute {
	public String getName() {
		return "Subsuming tree ID";
	}
	public String getDescription() {
		return "Represents the subsuming subtree ID";
	}
	public String evaluate(ContextTreeNode node) {
		return ((VisualTreeNode)node).getSubsumingSubtreeID();
	}
}

/*
 * public class SubtreeAttribute extends BooleanAttribute {
	public String getName() {
		return "Subsuming tree";
	}
	public String getDescription() {
		return "Represents the subsuming subtree";
	}
	public boolean evaluate(ContextTreeNode node) {
		return ((VisualTreeNode)node).isInSubsumingSubtree();
	}
 */
