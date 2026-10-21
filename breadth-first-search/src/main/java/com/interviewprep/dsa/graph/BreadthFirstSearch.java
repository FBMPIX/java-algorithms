package com.interviewprep.dsa.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class BreadthFirstSearch {
    private BreadthFirstSearch() { }

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

        ArrayList<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertexCount()];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        order.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.remove();

            for (int n : graph.neighbors(vertex)) {
                if (!visited[n]) {
                    visited[n] = true;
                    order.add(n);
                    queue.add(n);
                }
            }
        }

        return order;
    }

    public static boolean hasPath(Graph graph, int start, int target) {
        requireGraph(graph);
        graph.validateVertex(start);
        graph.validateVertex(target);

        boolean[] visited = new boolean[graph.vertexCount()];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.remove();

            for (int n : graph.neighbors(vertex)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        return visited[target];
    }

    public static List<Integer> shortestPath(Graph graph, int start, int target) {
        requireGraph(graph);
        graph.validateVertex(start);
        graph.validateVertex(target);


        LinkedList<Integer> path = new LinkedList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int edgeTo[] = new int[graph.vertexCount()];
        boolean[] visited = new boolean[graph.vertexCount()];

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();

            for (int n : graph.neighbors(vertex)) {
                if (!visited[n]) {
                    visited[n] = true;
                    edgeTo[n] = vertex;
                    queue.add(n);
                }
            }
        }

        if (!visited[target]) {
            return List.of();
        }

        for (int x = target; x != start; x = edgeTo[x])
            path.addFirst(x);
        path.addFirst(start);

        return path;
    }

    private static void requireGraph(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph cannot be null");
        }
    }
}
