package com.interviewprep.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class DepthFirstSearch {
    private DepthFirstSearch() {
    }

    public static final class Graph {
        private final List<List<Integer>> adjacency;

        public Graph(int vertexCount) {
            if (vertexCount < 0) {
                throw new IllegalArgumentException("vertexCount cannot be negative");
            }

            adjacency = new ArrayList<>();
            for (int i = 0; i < vertexCount; i++) {
                adjacency.add(new ArrayList<>());
            }
        }

        public int vertexCount() {
            return adjacency.size();
        }

        public void addUndirectedEdge(int a, int b) {
            validateVertex(a);
            validateVertex(b);

            if (a == b) {
                if (!adjacency.get(a).contains(a)) {
                    adjacency.get(a).add(a);
                }
                return;
            }
            if (!adjacency.get(a).contains(b)) {
                adjacency.get(a).add(b);
            }
            if (!adjacency.get(b).contains(a)) {
                adjacency.get(b).add(a);
            }
        }

        public List<Integer> neighbors(int vertex) {
            validateVertex(vertex);

            return new ArrayList<>(adjacency.get(vertex));
        }

        private void validateVertex(int vertex) {
            if (vertex < 0 || vertex >= adjacency.size()) {
                throw new IndexOutOfBoundsException("vertex out of range: " + vertex);
            }
        }
    }

    public static List<Integer> traversalOrder(Graph graph, int start) {
        requireGraph(graph);
        graph.validateVertex(start);
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] visited = new boolean[graph.vertexCount()];

        return traversalOrderHelper(path, visited, graph, start);
    }

    public static boolean hasPath(Graph graph, int start, int target) {
        requireGraph(graph);
        graph.validateVertex(start);
        graph.validateVertex(target);

        boolean[] visited = new boolean[graph.vertexCount()];

        return hasPathHelper(visited, graph, start, target);
    }

    public static List<Integer> pathTo(Graph graph, int start, int target) {
        requireGraph(graph);
        graph.validateVertex(start);
        graph.validateVertex(target);

        if (!hasPath(graph, start, target)) {
            return List.of();
        }

        LinkedList<Integer> path = new LinkedList<>();
        boolean[] visited = new boolean[graph.vertexCount()];
        int[] edgeTo = new int[graph.vertexCount()];

        edgeTo = pathToHelper(visited, edgeTo, graph, start, target);

        for (int x = target; x != start; x = edgeTo[x])
            path.addFirst(x);
        path.addFirst(start);

        return path;
    }

    private static List<Integer> traversalOrderHelper(LinkedList<Integer> path, boolean[] visited, Graph graph, int v) {
        visited[v] = true;
        path.add(v);

        for (int n : graph.neighbors(v)) {
            if (!visited[n]) {
                traversalOrderHelper(path, visited, graph, n);
            }
        }

        return path;
    }

    private static boolean hasPathHelper(boolean[] visited, Graph graph, int v, int target) {
        if (v == target) {
            return true;
        }

        visited[v] = true;

        for (int n : graph.neighbors(v)) {
            if (!visited[n]) {
                if (hasPathHelper(visited, graph, n, target)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int[] pathToHelper(boolean[] visited, int[] edgeTo, Graph graph, int v, int target) {
        visited[v] = true;

        for (int n : graph.neighbors(v)) {
            if (!visited[n]) {
                edgeTo[n] = v;
                pathToHelper(visited, edgeTo, graph, n, target);
            }
        }

        return edgeTo;
    }

    private static void requireGraph(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph cannot be null");
        }
    }
}
