package org.example.graph.scc;

import org.example.graph.model.Graph;
import org.example.metrics.Metrics;
import java.util.*;

public class KosarajuSCC {
    private final Graph g;
    private final Metrics m;
    private boolean[] used;
    private Stack<Integer> order = new Stack<>();
    private List<List<Integer>> comps;

    public KosarajuSCC(Graph g, Metrics m) {
        this.g = g;
        this.m = m;
        this.used = new boolean[g.n];
    }

    private void dfs1(int v) {
        m.sccDfsVisits++;
        used[v] = true;
        for (int[] e : g.adj.get(v)) {
            m.sccDfsEdges++;
            int to = e[0];
            if (!used[to]) dfs1(to);
        }
        order.push(v);
    }

    private void dfs2(int v, List<Integer> comp) {
        comp.add(v);
        used[v] = true;
        for (int[] e : g.revAdj.get(v)) {
            m.sccDfsEdges++;
            int to = e[0];
            if (!used[to]) dfs2(to, comp);
        }
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < g.n; i++)
            if (!used[i]) dfs1(i);

        Arrays.fill(used, false);
        comps = new ArrayList<>();

        while (!order.isEmpty()) {
            int v = order.pop();
            if (!used[v]) {
                List<Integer> comp = new ArrayList<>();
                dfs2(v, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    public Graph buildCondensationGraph() {
        return Graph.buildCondensation(g, comps);
    }
}
