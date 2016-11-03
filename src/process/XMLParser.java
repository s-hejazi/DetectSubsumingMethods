package process;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.Method;
import data.Node;
import data.Tree;


public class XMLParser {

	private ArrayList<Method> methodList = new ArrayList <Method>();
	private Map <String, Node> nodeDictionary = new HashMap();
	Tree CCT;
	
	
	org.w3c.dom.Node numberAttr;
	public void parse(File xmlFile){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document xmlDocument = dBuilder.parse(xmlFile);
			xmlDocument.getDocumentElement().normalize();
			
			NodeList nodeList = xmlDocument.getDocumentElement().getElementsByTagName("node");
			org.w3c.dom.Node XMLNode;
			if (nodeList != null && nodeList.getLength() > 0) {
			    for (int i = 0; i < nodeList.getLength(); i++) {
			        XMLNode = nodeList.item(i);			        
			        if (XMLNode instanceof Element) {
			            Element element = (Element) XMLNode;
			            element.setUserData("count", Integer.toString(i), null);
			            addNodeInfo(element);
			        }  
			    }
			    addChildren();
				createTree();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Create Node in the tree, update method list
	 * @param element
	 */
	private void addNodeInfo(Element element){
		//CHECK IF EXISTS?
		if (element != null) 
		{
			Node node = new Node();	
			//Create and Add method only if it has not been parsed before	
			// METHOD WITH SAME NAME??
			String methodName = element.getAttribute("methodName");
			int methodCost = Integer.parseInt(element.getAttribute("time"));
			node.setCost(methodCost);
			Method method = null;
			for(Method m: methodList)
			if(m.getLabel().equals(methodName)){
				method = m;
				break;
			}
			if(method == null){
			method = new Method(methodName);
			methodList.add(method);
			}			
			method.addNodes(node);
			//add isNode method and parent
			node.setMethod(method);	

			String parentKey = (String)((Element)element.getParentNode()).getUserData("count");
			if (parentKey!= null ){
				node.setParent(nodeDictionary.get(parentKey));
			}
			nodeDictionary.put((String) element.getUserData("count"), node);

	}
	}
	/**
	 * Find and store the children of each Node
	 */
	private void addChildren(){
		
		for (Node node1: nodeDictionary.values())
		{
			ArrayList<Node> childNodes = new ArrayList<Node>();
			if (node1.getParent() != null) 
			{
				for (Node node2: nodeDictionary.values())
				{
					if (node2.getParent() == node1) 
					{
						childNodes.add(node2);
					}
				}
			}
			node1.setChildren(childNodes);
		}
	}
	
	/**
	 * create CCT (tree class)
	 * @return Tree
	 */
	private void createTree(){
		Node root = null;
		for (Node node: nodeDictionary.values())
		{
			if(node.getParent() == null) 
			{
				root = node;
				break;
			}
		}
		for (Node node: nodeDictionary.values())
		{
			
			System.out.println(node.getMethod().getLabel());
			if(node.getParent()!= null)
			System.out.println("node's parent: " + node.getParent().getMethod().getLabel() );
			System.out.println("cost : " + node.getCost());
			for(int i = 0; i<node.getChildren().size(); i++)
				System.out.println(" node's children : "+ node.getChildren().get(i).getMethod().getLabel());
		}
		CCT = new Tree(root, methodList);
		System.out.println("CCT created");
	}
}
