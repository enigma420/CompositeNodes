package main;

import java.util.LinkedList;
import java.util.List;

public class MyStructure implements IMyStructure {

    private List<INode> nodes = new LinkedList<>();
    private int count = 0;

    public void addNode(Node node) {
        nodes.add(node);
    }

    private INode findNodeByCode(String code, List<INode> nodes){
        for (INode node:nodes) {
            if(node.getCode().equals(code)) return node;
            else if(node instanceof CompositeNode){
                INode foundNode = findNodeByCode(code,((CompositeNode)node).getNodes());
                if(foundNode != null) return foundNode;
            }
        }
        return null;
    }

    @Override
    public INode findByCode(String code) {
        if(code == null) throw new IllegalArgumentException("Cannot find node => Code is null");
        return findNodeByCode(code,nodes);
    }

    private INode findNodeByRenderer(String renderer, List<INode> nodes){
        for (INode node : nodes) {
            if(node.getRenderer().equals(renderer)) return node;
            else if(node instanceof CompositeNode){
                INode foundNode = findNodeByRenderer(renderer,((CompositeNode)node).getNodes());
                if(foundNode != null) return foundNode;
            }
        }
        return null;
    }

    @Override
    public INode findByRenderer(String renderer) {
        if(renderer == null) throw new IllegalArgumentException("Cannot find node => Renderer is null");
        return findNodeByRenderer(renderer,nodes);
    }

    private int countNodes(List<INode> nodes){
        for (INode node : nodes) {
            if(node instanceof CompositeNode){
                count++;
                countNodes(((CompositeNode)node).getNodes());
            }else if(node instanceof Node) count++;
        }
        return count;
    }

    @Override
    public int count() {
        return countNodes(nodes);
    }

}
