package main;


interface INode {

    String getCode();

    String getRenderer();

    int count();

    INode findByRenderer(String renderer);

    INode findByCode(String code);

}
