package main;

public interface IMyStructure {

    // returns the node with the given code or null
    INode findByCode(String code);

    // returns the node with the given render or null
    INode findByRenderer(String renderer);

    // returns the number of nodes
    int count();

}
