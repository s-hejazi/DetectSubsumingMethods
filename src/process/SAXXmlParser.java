/*package process;

import data.Node;

import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXXmlParser {
    public static void main(String[] args){

        try {
            File inputFile = new File("src/profiler/h2.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
            List<Node> nodeList = userhandler.getTreeWithNodeList();
            //print employee information
            printNodeList(nodeList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printNodeList(List<Node> nodeList){
        int i = 0;
        for(Node nodeElement : nodeList){
            i++;
            System.out.println("NodeIndex: "+i+ " ClassName : "+nodeElement.getClassName());
            System.out.println("node children: "+ nodeElement.getChildren());
        }
    }
}

*/