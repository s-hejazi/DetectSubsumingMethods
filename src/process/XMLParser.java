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
	private Map <String, Node> nodeDictionary = new HashMap <String, Node>();
	Tree CCT;
	org.w3c.dom.Node numberAttr;
	
	public Tree parse(File xmlFile){
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
			            element.setUserData("number", Integer.toString(i), null);
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
		return CCT;
	}	
	
	/**
	 * Create Node in the tree, update method list
	 * @param element
	 */
	private void addNodeInfo(Element element){

		int nodeInclusiveCost, nodeExclusiveCost = 0;
		if (element != null) 
		{
			Node node = new Node();	
			String methodName = element.getAttribute("methodName");
			String className = element.getAttribute("class");
			String signature = element.getAttribute("methodSignature");

			//int invocationCount = Integer.parseInt(element.getAttribute("count"));
			Method method = null;
			for(Method m: methodList)
			if(m.getLabel().equals(methodName) 
					&& m.getClassName().equals(className)
					&& m.getSignature().equals(signature)){
				method = m;
				break;
			}
			if(method == null){
			method = new Method(methodName);
			method.setClassName(className);
			method.setSignature(signature);
			methodList.add(method);
			}	
			//method.addInvocationCount(invocationCount);
			method.addNodes(node);
			node.setMethod(method);	
			
			//Inclusive cost
			if(element.hasAttribute("time")){
				nodeInclusiveCost = Integer.parseInt(element.getAttribute("time"));
				method.addInclusiveCost(nodeInclusiveCost);
		}
			//Exclusive cost
			if(element.hasAttribute("selfTime")){
				nodeExclusiveCost = Integer.parseInt(element.getAttribute("selfTime"));
				node.setCost(nodeExclusiveCost);
				method.addExclusiveCost(nodeExclusiveCost);
		}
			
			
			String parentKey = (String)((Element)element.getParentNode()).getUserData("number");
			if (parentKey!= null ){
				node.setParent(nodeDictionary.get(parentKey));
			}
			nodeDictionary.put((String) element.getUserData("number"), node);		

	}
	}
	/**
	 * Find and store the children of each Node
	 */
	private void addChildren(){
		
		for (Node node1: nodeDictionary.values())
		{
			ArrayList<Node> childNodes = new ArrayList<Node>();

				for (Node node2: nodeDictionary.values())
				{
					
					if (node2.getParent() != null && node2.getParent() == node1) 
					{
						childNodes.add(node2);
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

		System.out.println("Total node count: " + nodeDictionary.size());
		CCT = new Tree(root, methodList);
		//System.out.println("CCT created");
	}
}
