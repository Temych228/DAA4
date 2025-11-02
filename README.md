User Guide — Smart City Scheduling Project

Download the Project
Clone or download the repository and open it in your Java IDE (IntelliJ IDEA recommended).

git clone https://github.com/Temych228/DAA4.git
cd DAA_ASS4

Build the Project
Run Maven build to install dependencies and compile:

mvn clean install

Run the Main Program
Open org.example.app.Main or GraphRunner.java and run it.
This class manages the full workflow:

Reads graph data from /data/

Finds strongly connected components (SCC)

Builds a condensation DAG

Performs topological sorting

Computes shortest and longest paths

Input Data
Graphs are automatically generated or read from JSON files using GraphGenerator.
Each graph includes nodes and directed edges. You can modify generation parameters in the code to change graph size or density.

Running Specific Algorithms

SCC Detection: KosarajuSCC.java — identifies strongly connected components.

Topological Sort: TopoSort.java — produces a valid task order for acyclic graphs.

Shortest Path: DagShortestPath.java — calculates the minimal route in a DAG.

Longest Path: DagLongestPath.java — finds the critical (maximum-length) path.

You can run each of these classes individually for debugging or demonstration.

Metrics and Performance
The Metrics class automatically tracks DFS visits, edge relaxations, and execution time.
All metrics are printed in the console after execution.

Output
The program prints:

List of SCCs and their sizes

Topological order of components

Shortest and longest paths

Path lengths and metrics summary

Testing
Run all provided JUnit tests to verify correctness:

mvn test
Tests are located in /src/test/java and include SCC, TopoSort, and DAG path checks.

Modify and Extend
You can change dataset generation, add new graph files in /data/, or adjust algorithm parameters directly in the code for experiments.



Smart City 
1. Project Overview

This project combines two core graph theory problems — strongly connected components (SCC) and shortest paths in directed acyclic graphs (DAG) — within a single practical scenario.
It models task scheduling in a smart city or smart campus environment, where operations such as maintenance, cleaning, and analytics depend on each other.
The system identifies cyclic dependencies, compresses them into components, determines a valid execution order, and computes both the shortest and longest (critical) paths for efficient task planning.

2. Data Summary

To test all algorithms, nine datasets were generated using the built-in graph generator.
They vary by size and density, including both cyclic and acyclic structures.
Small datasets contain about six to ten nodes and are used for debugging.
Medium datasets include up to twenty nodes and several strongly connected components.
Large datasets reach fifty nodes and are used for performance testing.
Each dataset records the number of vertices, edges, and whether it contains cycles or forms a DAG.

3. Results

The program was tested on all datasets to evaluate correctness and performance.

During the SCC detection stage, the Kosaraju algorithm correctly identified all strongly connected components and produced accurate condensation graphs.
Execution time grew steadily with the number of edges, confirming the expected linear complexity.
Cyclic graphs required slightly more time due to repeated depth-first traversals, but performance remained stable.

In the topological sorting stage, Kahn’s algorithm successfully generated valid orders for all acyclic cases.
The algorithm proved efficient and predictable, as it relies on queue operations rather than recursion.
Even large datasets completed sorting within milliseconds.

The shortest and longest path computations in DAGs also produced correct results.
The shortest-path algorithm efficiently relaxed all reachable nodes in topological order, while the longest-path variant identified critical routes by maximizing path duration.
For larger graphs, the main cost came from traversing dense edge structures, but time remained acceptable for practical use.

4. Analysis

Each algorithm demonstrated stable scalability and clear performance trends.
SCC detection is the most sensitive to graph density, since additional cycles trigger more DFS operations.
Topological sorting is the fastest stage, with performance largely independent of graph complexity.
The DAG path algorithms balance between accuracy and cost, with their efficiency directly tied to the size of the topologically sorted structure.

When graph structures are sparse or nearly acyclic, all methods perform exceptionally well.
In contrast, highly cyclic graphs increase the workload for SCC detection but have limited effect on later stages.
This confirms that compressing cycles early (via SCC condensation) is essential for overall optimization.

5. Conclusions

The project successfully implements a complete scheduling pipeline for directed dependency graphs.
Strongly connected component detection helps isolate cycles and simplify the dependency network.
Topological ordering then ensures that every task is executed only after its prerequisites are complete.
Finally, the shortest and longest path algorithms allow users to find optimal and critical sequences of operations.

From a practical standpoint:

SCC algorithms are best suited for detecting and simplifying complex cyclic dependencies.

Topological sorting is ideal for execution planning in purely acyclic environments.

Shortest path algorithms support efficient resource or time optimization, while longest path calculations reveal bottlenecks and critical routes.

Overall, the system performs consistently across different graph sizes and structures, making it a reliable foundation for scheduling and optimization tasks in smart city or smart campus environments.
