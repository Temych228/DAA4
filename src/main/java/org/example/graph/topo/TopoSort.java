package org.example.graph.topo;

import org.example.graph.model.Graph;
import org.example.metrics.Metrics;
import java.util.*;

public class TopoSort {
    public static List<Integer> sort(Graph g, Metrics m) {
        int[] indeg = new int[g.n];
        for (int u = 0; u < g.n; u++)
            for (int[] e : g.adj.get(u))
                indeg[e[0]]++;

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < g.n; i++)
            if (indeg[i] == 0) {
                q.add(i);
                m.topoPushes++;
            }

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            m.topoPops++;
            order.add(u);

            for (int[] e : g.adj.get(u)) {
                int v = e[0];
                if (--indeg[v] == 0) {
                    q.add(v);
                    m.topoPushes++;
                }
            }
        }
        return order;
    }
}
