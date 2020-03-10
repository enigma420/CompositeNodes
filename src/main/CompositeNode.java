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

    @Override
    public int count() {
        int result = 0;

        for (INode node : nodes) {
            result += node.count();
        }
        result += super.count();

        return result;
    }

    @Override
    public INode findByCode(String code) {

        for (INode node : nodes) {
            INode result = node.findByCode(code);
            if (result != null) {
                return result;
            }
        }
        return super.findByCode(code);
    }
}