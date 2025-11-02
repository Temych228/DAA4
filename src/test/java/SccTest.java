import org.example.graph.model.Graph;
import org.example.graph.scc.KosarajuSCC;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SccTest {

    @Test
    public void testSingleSCC() {
        Graph g = new Graph(3);
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,0,1);

        Metrics m = new Metrics();
        KosarajuSCC scc = new KosarajuSCC(g, m);
        List<List<Integer>> comps = scc.findSCCs();

        assertEquals(1, comps.size());
        assertEquals(3, comps.get(0).size());
    }

    @Test
    public void testTwoSCCs() {
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,0,1);
        g.addEdge(2,3,1);
        g.addEdge(3,2,1);

        Metrics m = new Metrics();
        KosarajuSCC scc = new KosarajuSCC(g, m);
        List<List<Integer>> comps = scc.findSCCs();

        assertEquals(2, comps.size());
    }

    @Test
    public void testDisconnectedNodes() {
        Graph g = new Graph(3);

        Metrics m = new Metrics();
        KosarajuSCC scc = new KosarajuSCC(g, m);
        List<List<Integer>> comps = scc.findSCCs();

        assertEquals(3, comps.size());
    }
}
