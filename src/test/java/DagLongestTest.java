import org.example.graph.model.Graph;
import org.example.graph.dag.DagLongestPath;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DagLongestTest {

    @Test
    public void testLongestPathSimple() {
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,2,2);
        g.addEdge(2,3,3);

        Metrics m = new Metrics();
        var res = DagLongestPath.findLongest(g, m);

        assertEquals(6, res.length);
        assertEquals(java.util.List.of(0,1,2,3), res.path);
    }
}
