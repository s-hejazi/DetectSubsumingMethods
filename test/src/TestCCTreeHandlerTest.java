import data.Node;
import org.junit.Test;
import process.CCTreeHandler;

import java.util.ArrayList;

import static junit.framework.Assert.*;

public class TestCCTreeHandlerTest extends CCTreeHandler {

    @Test
    public void testRecursivePath(){
        CCTreeHandler cctHandler = new CCTreeHandler();
        Node mainNode = new Node();
        Node childA0 = new Node();
        childA0.setMethodName("child a");
        Node childB0 = new Node();
        childB0.setParent(childA0);
        Node childC0 = new Node();
        childC0.setParent(childA0);
        childB0.setMethodName("child b");
        childC0.setMethodName("child c");


        ArrayList<Node> levelZeroChildNodes = new ArrayList<>();
        levelZeroChildNodes.add(childB0);
        levelZeroChildNodes.add(childC0);
        childA0.setChildren(levelZeroChildNodes);
        ArrayList<Node> level0Nodes = new ArrayList<>();
        level0Nodes.add(childA0);

        Node childA1 = new Node();
        Node childB1 = new Node();
        Node childC1 = new Node();
        ArrayList<Node> levelOneChildNodes = new ArrayList<>();
        levelOneChildNodes.add(childB1);
        levelOneChildNodes.add(childC1);
        // add a function to set the parent of the child nodes when setting children
        childA1.setChildren(levelOneChildNodes);
        childB1.setChildren(levelZeroChildNodes);

        childB1.setParent(childA1);
        childC1.setParent(childA1);
        childA0.setParent(childB1);
        ArrayList<Node> level1Nodes = new ArrayList<>();
        level1Nodes.add(childA1);

        Node childA2 = new Node();
        Node childB2 = new Node();
        Node childC2 = new Node();
        ArrayList<Node> levelTwoChildNodes = new ArrayList<>();
        levelTwoChildNodes.add(childB2);
        levelTwoChildNodes.add(childC2);
        childB2.setParent(childA2);
        childC2.setParent(childA2);
        childA2.setChildren(levelTwoChildNodes);

        ArrayList<Node> level2Nodes = new ArrayList<>();
        level2Nodes.add(childA2);
        childB2.setChildren(level2Nodes);
        childA1.setParent(childB2);

        Node childA3 = new Node();
        Node childB3 = new Node();
        Node childC3 = new Node();
        ArrayList<Node> levelThreeChildNodes = new ArrayList<>();
        levelThreeChildNodes.add(childB3);
        levelThreeChildNodes.add(childC3);
        childA3.setChildren(levelThreeChildNodes);
        childB3.setParent(childA3);
        childC3.setParent(childA3);
        childA2.setParent(childB3);
        ArrayList<Node> level3Nodes = new ArrayList<>();
        level3Nodes.add(childA3);
        childA3.setParent(mainNode);
        mainNode.setChildren(level0Nodes);


        mainNode.setLeaf("leaf node");
        mainNode.setClassName("Test Class");
        mainNode.setMethodName("Test method");
        System.out.println("before adjusted main node >>>"+ mainNode.getChildren().size());
        Node adjustedMainNode = cctHandler.findAdjustedParent(childA1);
       // assertNotNull(adjustedMainNode);
        System.out.println("adjusted main node >>>"+ adjustedMainNode);
        //assertNotNull(adjustedMainNode.getAdjustedChildern().size());


        //mainNode.setChildren();

    }

}