import org.example.graph.model.Graph;
import org.example.graph.dag.DagShortestPath;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DagShortestTest {

    @Test
    public void testShortestPathSimple() {
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,2,2);
        g.addEdge(2,3,3);

        Metrics m = new Metrics();
        int[] dist = DagShortestPath.findShortest(g, 0, m);

        assertArrayEquals(new int[]{0,1,3,6}, dist);
    }

    @Test
    public void testUnreachable() {
        Graph g = new Graph(3);
        g.addEdge(0,1,5);
        // Node 2 disconnected

        Metrics m = new Metrics();
        int[] dist = DagShortestPath.findShortest(g, 0, m);

        assertEquals(0, dist[0]);
        assertEquals(5, dist[1]);
        assertTrue(dist[2] >= 1_000_000_000);
    }
}
