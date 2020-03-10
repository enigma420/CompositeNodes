package test;

import main.CompositeNode;
import main.MyStructure;
import main.Node;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyStructureTest {

    /* Declare a few NODE instances */
    private static final Node FIRST_NODE = new Node("first_code", "first_renderer");
    private static final Node SECOND_NODE = new Node("second_code", "second_renderer");
    private static final Node THIRD_NODE = new Node("third_code", "third_renderer");
    private static final Node FOURTH_NODE = new Node("fourth_code", "fourth_renderer");
    private static final Node FIFTH_NODE = new Node("fifth_code", "fifth_renderer");
    private static final Node SIXTH_NODE = new Node("sixth_code", "sixth_renderer");

    /* Declare a few COMPOSITE_NODE instances */
    private static final CompositeNode FIRST_COMPOSITE_NODE = new CompositeNode("first_composite_code", "first_composite_renderer");
    private static final CompositeNode SECOND_COMPOSITE_NODE = new CompositeNode("second_composite_code", "second_composite_renderer");
    private static final CompositeNode THIRD_COMPOSITE_NODE = new CompositeNode("third_composite_code", "third_composite_renderer");
    private static final CompositeNode FOURTH_COMPOSITE_NODE = new CompositeNode("fourth_composite_code", "fourth_composite_renderer");

    /*
    Declare two structures with MyStructure type where:
    - emptyStruct is empty but necessary for testing:
    1. notNullValue of created instance
    2. Return 0 when use method count()
    - filledStruct is fill with nested nodes which show up in next part of test class.
    1. findBy(Code or Renderer) nodes,composite_nodes,nested nodes,nested composite_nodes,multiple nested nodes,multiple nested composite_nodes with null or properly code/renderer
    2. Report exception when pass null as code or renderer during findBy(Code/Renderer)
    3. Return properly count of single composite_node and the whole structure
    */
    private MyStructure emptyStruct = new MyStructure();
    private MyStructure filledStruct;

    /* At beginning we must create nested nodes structure */
    @BeforeAll
    static void setUpNestedNodes() {
        SECOND_COMPOSITE_NODE.addNode(THIRD_NODE);
        FIRST_COMPOSITE_NODE.addNode(FOURTH_NODE);
        FIRST_COMPOSITE_NODE.addNode(FIFTH_NODE);
        THIRD_COMPOSITE_NODE.addNode(SIXTH_NODE);
        FIRST_COMPOSITE_NODE.addNode(THIRD_COMPOSITE_NODE);
        SECOND_COMPOSITE_NODE.addNode(FIRST_COMPOSITE_NODE);

    }

    /* Before each test we must to add nodes/composite_nodes into filledStruct */
    @BeforeEach
    void addNodesAndCompositeNodesIntoStruct() {
        filledStruct = new MyStructure();
        filledStruct.addNode(FIRST_NODE);
        filledStruct.addNode(SECOND_NODE);
        filledStruct.addNode(SECOND_COMPOSITE_NODE);
        filledStruct.addNode(FOURTH_COMPOSITE_NODE);
    }

    /*
    emptyStruct
    |
    |
    |
    |
    end

    count: 0
     */

    /*
    filledStruct
    |
    |
    --FIRST_NODE
    |
    --SECOND_NODE
    |
    ----SECOND_COMPOSITE_NODE------FIRST_COMPOSITE_NODE------THIRD_COMPOSITE_NODE
    |              |                         |                         |
    |          THIRD_NODE                FOURTH_NODE               SIXTH_NODE
    |                                        |
    |                                    FIFTH_NODE
    |
    ----FOURTH_COMPOSITE_NODE
    |
    |
    end

     [FIRST_NODE,SECOND_NODE,
     [SECOND_COMPOSITE_NODE,THIRD_NODE,
     [FIRST_COMPOSITE_NODE,FOURTH_NODE,FIFTH_NODE,
     [THIRD_COMPOSITE_NODE,SIXTH_NODE]
     ]
     ]
     FOURTH_COMPOSITE_NODE]
    count: 10
     */


    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        assertThat(emptyStruct, is(notNullValue()));
    }

    @Test
    void shouldReturn0WhenStructureIsEmpty() {
        assertThat(emptyStruct.count(), is(equalTo(0)));
    }

    @Test
    void shouldReturnNullWhenNotFoundByCode() {
        assertThat(filledStruct.findByCode("SOME_CODE"), is(nullValue()));
    }

    @Test
    void shouldReturnNullWhenNotFoundByRenderer() {
        assertThat(filledStruct.findByRenderer("SOME_RENDERER"), is(nullValue()));
    }

    @Test
    void shouldReturnNodeWhenFoundByCode() {
        assertThat(filledStruct.findByCode("third_code"), is(THIRD_NODE));
    }

    @Test
    void shouldReturnNodeWhenFoundByRenderer() {
        assertThat(filledStruct.findByRenderer("first_renderer"), is(FIRST_NODE));
    }

    @Test
    void shouldReportIllegalArgumentExceptionWhenPassNullAsCode() {
        assertThrows(IllegalArgumentException.class, () -> filledStruct.findByCode(null));
    }

    @Test
    void shouldReportIllegalArgumentExceptionWhenPassNullAsRenderer() {
        assertThrows(IllegalArgumentException.class, () -> filledStruct.findByRenderer(null));
    }

    @Test
    void shouldFindCompositeNodeByCode() {
        assertThat(filledStruct.findByCode("second_composite_code"), is(SECOND_COMPOSITE_NODE));
    }

    @Test
    void shouldFindCompositeNodeByRenderer() {
        assertThat(filledStruct.findByRenderer("second_composite_renderer"), is(SECOND_COMPOSITE_NODE));
    }

    @Test
    void shouldFindNestedCompositeNodeByCode() {
        assertThat(filledStruct.findByCode("first_composite_code"), is(FIRST_COMPOSITE_NODE));
    }

    @Test
    void shouldFindNestedCompositeNodeByRenderer() {
        assertThat(filledStruct.findByRenderer("first_composite_renderer"), is(FIRST_COMPOSITE_NODE));
    }

    @Test
    void shouldFindMultipleNestedCompositeNodeByCode() {
        assertThat(filledStruct.findByCode("third_composite_code"), is(THIRD_COMPOSITE_NODE));
    }

    @Test
    void shouldFindMultipleNestedCompositeNodeByRenderer() {
        assertThat(filledStruct.findByRenderer("third_composite_renderer"), is(THIRD_COMPOSITE_NODE));
    }

    @Test
    void shouldReturnEmptyListWhenGetNodesFromEmptyCompositeNode() {
        assertThat(FOURTH_COMPOSITE_NODE.getNodes(), is(empty()));
    }

    @Test
    void shouldFindNestedNodeByCode() {
        assertThat(filledStruct.findByCode("third_code"), is(THIRD_NODE));
    }

    @Test
    void shouldFindNestedNodeByRenderer() {
        assertThat(filledStruct.findByRenderer("third_renderer"), is(THIRD_NODE));
    }

    @Test
    void shouldFindMultipleNestedNodeByCode() {
        assertThat(filledStruct.findByCode("fifth_code"), is(FIFTH_NODE));
    }

    @Test
    void shouldFindMultipleNestedNodeByRenderer() {
        assertThat(filledStruct.findByRenderer("fourth_renderer"), is(FOURTH_NODE));
    }

    @Test
    void shouldProperlyCountMultipleNestedStructure() {
        assertThat(filledStruct.count(), is(10));
    }

    /* In the next case we must substract 1 because we dont count CompositeNode */
    @Test
    void shouldProperlyCountNodesLocatedInCompositeNodeWhichOnlyHaveNodes() {
        assertThat(THIRD_COMPOSITE_NODE.count() - 1, is(1));
    }


}

