package org.example.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GraphGenerator {
    private static final Random rnd = new Random(12345);

    public static void writeJson(String path, int n, List<int[]> edges, int source) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"directed\": true,\n  \"n\": ").append(n).append(",\n  \"edges\": [\n");
        for (int i = 0; i < edges.size(); i++) {
            int[] e = edges.get(i);
            sb.append("    {\"u\": ").append(e[0]).append(", \"v\": ").append(e[1]).append(", \"w\": ").append(e[2]).append("}");
            if (i < edges.size()-1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ],\n  \"source\": ").append(source).append(",\n  \"weight_model\": \"edge\"\n}\n");
        Files.write(Paths.get(path), sb.toString().getBytes());
    }

    public static List<int[]> chain(int n, int startW) {
        List<int[]> e = new ArrayList<>();
        for (int i=0;i<n-1;i++) e.add(new int[]{i, i+1, startW + rnd.nextInt(3)});
        return e;
    }
}
