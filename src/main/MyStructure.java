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
        if (code == null) {
            throw new IllegalArgumentException("Cannot find node => Code is null");
        }
        for (INode node : nodes) {
            INode result = node.findByCode(code);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public INode findByRenderer(String renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Cannot find node => Renderer is null");
        }
        for (INode node : nodes) {
            INode result = node.findByRenderer(renderer);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public int count() {
        int result = 0;
        if (!nodes.isEmpty()) {
            for (INode node : nodes) {
                if (node != null) {
                    result += node.count();
                }
            }
        }

        return result;
    }

}
