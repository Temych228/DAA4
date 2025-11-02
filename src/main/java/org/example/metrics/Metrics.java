package org.example.metrics;

public class Metrics {
    public long startTime;
    public long elapsedNs;

    public long sccDfsVisits = 0;
    public long sccDfsEdges = 0;

    public long topoPushes = 0;
    public long topoPops = 0;

    public long dagRelaxations = 0;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        elapsedNs = System.nanoTime() - startTime;
    }

    public String timeMs() {
        return String.format("%.3f ms", elapsedNs / 1e6);
    }
}
