/*package process;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import data.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UserHandler extends DefaultHandler {

    boolean isNode = false;
    boolean tree = false;

    public List<Node> getTreeWithNodeList() {
        return treeWithNodeList;
    }

    private List<Node> treeWithNodeList;
    //private Node node = null;
    private Stack<Node> nodeStack;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(qName.equalsIgnoreCase("tree")){
                nodeStack = new Stack<Node>();
                treeWithNodeList = new ArrayList<>();
            }
            if(qName.equalsIgnoreCase("node")){

                Node currentNode = new Node();
                String leaf = attributes.getValue("leaf");
                String className = attributes.getValue("class");
                String methodName = attributes.getValue("methodName");
                String methodSignature = attributes.getValue("methodSignature");
                currentNode.setLeaf(leaf);
                currentNode.setClassName(className);
                currentNode.setMethodName(methodName);
                currentNode.setMethodSignature(methodSignature);
                nodeStack.push(currentNode);
            }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("node")) {
            treeWithNodeList.add(node);
        }
        if(qName.equalsIgnoreCase("node")){
            Node currentNode = nodeStack.pop();
            if(nodeStack.isEmpty()){
                treeWithNodeList.add(currentNode);
            }else{
                Node parentNode = nodeStack.peek();
                parentNode.addChildNode(currentNode);
                treeWithNodeList.add(currentNode);
            }
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (isNode) {
            System.out.println("First Name: " + new String(ch, start, length));
            isNode = false;
        }
    }
}
*/