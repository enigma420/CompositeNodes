package main;

import java.util.LinkedList;
import java.util.List;

public class MyStructure implements IMyStructure {

    private List<INode> nodes = new LinkedList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public INode findByCode(String code) {
        return null;
    }

    @Override
    public INode findByRenderer(String renderer) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
