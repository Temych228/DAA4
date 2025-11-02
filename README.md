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