package main;

import java.util.stream.Stream;

public class Node implements INode {

    private final String code;
    private final String renderer;

    public Node(String code, String renderer) {
        this.code = code;
        this.renderer = renderer;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getRenderer() {
        return renderer;
    }

    @Override
    public int count() {
        return (int) Stream.of(this).count();
    }

    @Override
    public INode findByRenderer(String renderer) {
        return null;
    }

    @Override
    public INode findByCode(String code) {
        if (this.code.equals(code)) {
            return this;
        }
        return null;
    }
}
