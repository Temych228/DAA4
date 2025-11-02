package org.example.graph.dag;

import org.example.graph.model.Graph;
import org.example.graph.topo.TopoSort;
import org.example.metrics.Metrics;
import java.util.*;

public class DagShortestPath {
    public static int[] findShortest(Graph g, int src, Metrics m) {
        List<Integer> topo = TopoSort.sort(g, m);
        int INF = 1_000_000_000;
        int[] dist = new int[g.n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int u : topo)
            if (dist[u] != INF)
                for (int[] e : g.adj.get(u)) {
                    int v = e[0], w = e[1];
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        m.dagRelaxations++;
                    }
                }

        return dist;
    }
}
