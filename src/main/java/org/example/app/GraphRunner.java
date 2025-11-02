package org.example.app;

import org.example.graph.model.Graph;
import org.example.graph.scc.KosarajuSCC;
import org.example.graph.topo.TopoSort;
import org.example.graph.dag.*;
import org.example.metrics.Metrics;

public class GraphRunner {
    public void run(String file) {
        System.out.println("\n=== Dataset: " + file + " ===");

        Metrics m = new Metrics();
        m.start();

        Graph g = Graph.loadFromJson(file);

        var scc = new KosarajuSCC(g, m);
        var comps = scc.findSCCs();
        Graph dag = scc.buildCondensationGraph();

        var topo = TopoSort.sort(dag, m);
        var dist = DagShortestPath.findShortest(dag, 0, m);
        var longest = DagLongestPath.findLongest(dag, m);

        m.stop();

        System.out.println("SCC count: " + comps.size());
        System.out.println("Time: " + m.timeMs());
        System.out.println("SCC visits/edges: " + m.sccDfsVisits + "/" + m.sccDfsEdges);
        System.out.println("Topo pushes/pops: " + m.topoPushes + "/" + m.topoPops);
        System.out.println("Relaxations: " + m.dagRelaxations);
        System.out.println("Critical path length: " + longest.length + " | Path = " + longest.path);
    }
}
