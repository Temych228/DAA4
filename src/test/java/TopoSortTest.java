import org.example.graph.model.Graph;
import org.example.graph.topo.TopoSort;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TopoSortTest {

    @Test
    public void testTopoSimple() {
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,3,1);

        Metrics m = new Metrics();
        List<Integer> order = TopoSort.sort(g, m);

        assertEquals(List.of(0,1,2,3), order);
    }

    @Test
    public void testTopoBranches() {
        Graph g = new Graph(5);
        g.addEdge(0,2,1);
        g.addEdge(1,2,1);
        g.addEdge(2,3,1);
        g.addEdge(2,4,1);

        Metrics m = new Metrics();
        List<Integer> order = TopoSort.sort(g, m);

        assertTrue(order.indexOf(0) < order.indexOf(2));
        assertTrue(order.indexOf(1) < order.indexOf(2));
    }
}
