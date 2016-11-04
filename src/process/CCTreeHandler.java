package process;

import data.Node;

public class CCTreeHandler {

    public void reduceRecursivePath(Node node){
        Node adjustedParent = findAdjustedParent(node);
        node.getAdjustedParent().getAdjustedChildern().add(node);

        for(Node childNode : node.getChildren()){
            reduceRecursivePath(childNode);
        }
    }

    public Node findAdjustedParent(Node currentNode){
        Node match1 = null;
        Node match2 = null;
        Node currentParent = currentNode.getParent();
        while(currentParent!= null && match2 == null){
            // Check in paper if method or method name should be compared
            if(currentParent.getMethod() == currentNode.getMethod()){
                if(match1 == null){
                    match1 = currentParent;
                }else{
                    match2 = currentParent;
                }
                currentParent = currentParent.getAdjustedParent();
            }
        }

        if(match1 == null || match2 == null){
            return currentNode.getParent();
        }
        Node node1 = currentNode.getParent();
        Node node2 = match1.getAdjustedParent();

        while(node1 != match1 && node2 != match2){
            if(node1.getMethod() != node2.getMethod()){
                return currentNode.getParent();
            }
            node1 = node1.getAdjustedParent();
            node2 = node2.getAdjustedParent();
        }
        if(node1 != match1 || node2 != match2){
            return currentParent;
        }
        return match1.getAdjustedParent();
    }
}
