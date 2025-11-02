package org.example.graph.model;

import org.json.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public int n;
    public List<List<int[]>> adj = new ArrayList<>();
    public List<List<int[]>> revAdj = new ArrayList<>();

    public Graph(int n) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new int[]{v, w});
        revAdj.get(v).add(new int[]{u, w});
    }

    public static Graph loadFromJson(String file) {
        try {
            InputStream in = Graph.class.getClassLoader().getResourceAsStream(file);
            if (in == null) throw new RuntimeException("File not found: " + file);
            String text = new String(in.readAllBytes());

            JSONObject o = new JSONObject(text);
            int n = o.getInt("n");
            Graph g = new Graph(n);

            var edges = o.getJSONArray("edges");
            for (int i = 0; i < edges.length(); i++) {
                var e = edges.getJSONObject(i);
                g.addEdge(e.getInt("u"), e.getInt("v"), e.getInt("w"));
            }
            return g;
        } catch (Exception e) {
            throw new RuntimeException("JSON error: " + e.getMessage());
        }
    }

    public static Graph buildCondensation(Graph g, List<List<Integer>> comps) {
        int c = comps.size();
        Graph dag = new Graph(c);
        Map<Integer,Integer> compId = new HashMap<>();

        for (int i = 0; i < c; i++)
            for (int v : comps.get(i)) compId.put(v, i);

        for (int u = 0; u < g.n; u++)
            for (int[] e : g.adj.get(u)) {
                int v = e[0], w = e[1];
                int cu = compId.get(u), cv = compId.get(v);
                if (cu != cv) dag.addEdge(cu, cv, w);
            }
        return dag;
    }
}
