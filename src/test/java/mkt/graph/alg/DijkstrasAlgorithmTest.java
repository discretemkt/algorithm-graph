package mkt.graph.alg;

import java.util.Arrays;
import java.util.List;
import mkt.graph.Digraph;
import mkt.graph.Graph;
import mkt.graph.impl.Factory;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * 
 * @since 0.0.1
 * @author mkt
 */
public class DijkstrasAlgorithmTest {
    
    @Test
    public void testFindShortestPathOfWeightedUndirectedGraph() {
        Graph<String> graph = Factory.createGraph();
        graph.connect("s", "a").setWeight(3.0);
        graph.connect("s", "b").setWeight(5.0);
        graph.connect("a", "b").setWeight(1.0);
        graph.connect("a", "c").setWeight(10.0);
        graph.connect("a", "d").setWeight(11.0);
        graph.connect("b", "a").setWeight(3.0);
        graph.connect("b", "c").setWeight(2.0);
        graph.connect("b", "d").setWeight(3.0);
        graph.connect("c", "d").setWeight(2.0);
        graph.connect("c", "e").setWeight(7.0);
        graph.connect("c", "f").setWeight(12.0);
        graph.connect("d", "a").setWeight(15.0);
        graph.connect("d", "b").setWeight(7.0);
        graph.connect("d", "f").setWeight(2.0);
        graph.connect("e", "d").setWeight(11.0);
        graph.connect("e", "z").setWeight(2.0);
        graph.connect("f", "e").setWeight(3.0);
        graph.connect("f", "z").setWeight(2.0);
        List<String> actual = DijkstrasAlgorithm.findShortestPath(graph, "s", "z");
        List<String> expected = Arrays.asList("s", "a", "b", "d", "f", "z");
        assertThat(actual, is(expected));
    }
    
    @Test
    public void testFindShortestPathOfWeightedDirectedGraph() {
        Digraph<String> graph = Factory.createDigraph();
        graph.connect("s", "a").setWeight(3.0);
        graph.connect("s", "b").setWeight(5.0);
        graph.connect("a", "b").setWeight(1.0);
        graph.connect("a", "c").setWeight(10.0);
        graph.connect("a", "d").setWeight(11.0);
        graph.connect("b", "a").setWeight(3.0);
        graph.connect("b", "c").setWeight(2.0);
        graph.connect("b", "d").setWeight(3.0);
        graph.connect("c", "d").setWeight(2.0);
        graph.connect("c", "e").setWeight(7.0);
        graph.connect("c", "f").setWeight(12.0);
        graph.connect("d", "a").setWeight(15.0);
        graph.connect("d", "b").setWeight(7.0);
        graph.connect("d", "f").setWeight(2.0);
        graph.connect("e", "d").setWeight(11.0);
        graph.connect("e", "z").setWeight(2.0);
        graph.connect("f", "e").setWeight(3.0);
        graph.connect("f", "z").setWeight(2.0);
        List<String> actual = DijkstrasAlgorithm.findShortestPath(graph, "s", "z");
        List<String> expected = Arrays.asList("s", "a", "b", "d", "f", "z");
        assertThat(actual, is(expected));
    }
    
}
