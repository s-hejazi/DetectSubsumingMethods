package visualizer;

import java.util.ArrayList;

import javax.swing.JFrame;

import ch.usi.inf.sape.trevis.model.attribute.InclusiveLongAttribute;
import ch.usi.inf.sape.trevis.model.attribute.ToString;
import ch.usi.inf.sape.trevis.swing.RadialRenderer;
import ch.usi.inf.sape.trevis.swing.TreeView;
import ch.usi.inf.sape.trevis.swing.TreeViewRenderer;
import data.Tree;



public class VisualizeMain {

	public static void createTreeView(Tree CCT, ArrayList<String> methodList) {
		final VisualTree tree = new VisualTree(CCT, methodList);
		final JFrame frame = new JFrame("Tree View");
		final TreeView view = new TreeView(new TreeViewRenderer[] {
				  new RadialRenderer()
		});
		view.setTree(tree);
		view.setSizeAttribute(new InclusiveLongAttribute(new WeightAttribute()));
		view.setHueAttribute(new ToString(new SubtreeAttribute()));

		
		frame.add(view);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	


}
