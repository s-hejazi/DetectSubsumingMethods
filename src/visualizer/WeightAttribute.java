package visualizer;

import ch.usi.inf.sape.trevis.model.ContextTreeNode;
import ch.usi.inf.sape.trevis.model.attribute.LongAttribute;

public class WeightAttribute extends LongAttribute {
	public String getName() {
		return "Exclusive weight";
	}
	public String getDescription() {
		return "Represents the exclusive weight of the given tree node";
	}
	public long evaluate(ContextTreeNode node) {
		return ((VisualTreeNode)node).getWeight();
	}
}