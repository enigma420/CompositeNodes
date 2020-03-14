package main;

import java.util.*;

public class CompositeNode extends Node implements ICompositeNode {

    private List<INode> nodes = new LinkedList<>();

    public CompositeNode(String code, String renderer) {
        super(code, renderer);
    }

    public void addNode(INode node) {
        nodes.add(node);
    }

    @Override
    public List<INode> getNodes() {
        return nodes;
    }

}