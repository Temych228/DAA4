package org.example.graph.dag;

import org.example.graph.model.Graph;
import org.example.metrics.Metrics;
import org.example.graph.topo.TopoSort;
import java.util.*;

public class DagLongestPath {
    public static class Result {
        public int length;
        public List<Integer> path;
        public Result(int l, List<Integer> p) { length = l; path = p; }
    }

    public static Result findLongest(Graph g, Metrics m) {
        List<Integer> topo = TopoSort.sort(g, m);
        int[] dp = new int[g.n];
        int[] parent = new int[g.n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        Arrays.fill(parent, -1);
        dp[0] = 0;

        for (int u : topo)
            if (dp[u] != Integer.MIN_VALUE)
                for (int[] e : g.adj.get(u)) {
                    int v = e[0], w = e[1];
                    if (dp[u] + w > dp[v]) {
                        dp[v] = dp[u] + w;
                        parent[v] = u;
                        m.dagRelaxations++;
                    }
                }

        int best = 0, end = 0;
        for (int i = 0; i < g.n; i++)
            if (dp[i] > best) { best = dp[i]; end = i; }

        List<Integer> path = new ArrayList<>();
        while (end != -1) {
            path.add(end);
            end = parent[end];
        }
        Collections.reverse(path);

        return new Result(best, path);
    }
}
