package com.interviewprep.dsa.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class BreadthFirstSearchTest {
    @Test
    void rejectsNegativeVertexCount() {
        assertThrows(IllegalArgumentException.class, () -> new BreadthFirstSearch.Graph(-1));
    }

    @Test
    void startsWithEmptyAdjacencyLists() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(3);

        assertEquals(3, graph.vertexCount());
        assertEquals(List.of(), graph.neighbors(0));
        assertEquals(List.of(), graph.neighbors(1));
        assertEquals(List.of(), graph.neighbors(2));
    }

    @Test
    void rejectsInvalidVertices() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(3);

        assertThrows(IndexOutOfBoundsException.class, () -> graph.addUndirectedEdge(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.addUndirectedEdge(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.neighbors(3));
        assertThrows(IndexOutOfBoundsException.class, () -> BreadthFirstSearch.traversalOrder(graph, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> BreadthFirstSearch.hasPath(graph, 0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> BreadthFirstSearch.shortestPath(graph, 3, 0));
        assertThrows(IllegalArgumentException.class, () -> BreadthFirstSearch.traversalOrder(null, 0));
    }

    @Test
    void addsUndirectedEdgesInBothDirections() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(4);

        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(2, 3);

        assertEquals(List.of(1, 2), graph.neighbors(0));
        assertEquals(List.of(0), graph.neighbors(1));
        assertEquals(List.of(0, 3), graph.neighbors(2));
        assertEquals(List.of(2), graph.neighbors(3));
    }

    @Test
    void preservesNeighborInsertionOrder() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(5);

        graph.addUndirectedEdge(0, 3);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 4);
        graph.addUndirectedEdge(0, 2);

        assertEquals(List.of(3, 1, 4, 2), graph.neighbors(0));
    }

    @Test
    void ignoresDuplicateEdgesAndStoresSelfLoopOnce() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(3);

        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(1, 0);
        graph.addUndirectedEdge(2, 2);

        assertEquals(List.of(1), graph.neighbors(0));
        assertEquals(List.of(0), graph.neighbors(1));
        assertEquals(List.of(2), graph.neighbors(2));
    }

    @Test
    void neighborsReturnsDefensiveCopy() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(3);
        graph.addUndirectedEdge(0, 1);

        List<Integer> neighbors = graph.neighbors(0);
        neighbors.add(2);

        assertEquals(List.of(1), graph.neighbors(0));
    }

    @Test
    void traversalVisitsInBreadthFirstOrder() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(0, 1, 2, 3, 4, 5), BreadthFirstSearch.traversalOrder(graph, 0));
    }

    @Test
    void traversalSkipsUnreachableVertices() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(6), BreadthFirstSearch.traversalOrder(graph, 6));
        assertFalse(BreadthFirstSearch.traversalOrder(graph, 0).contains(6));
    }

    @Test
    void traversalHandlesSingleVertexGraph() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(1);

        assertEquals(List.of(0), BreadthFirstSearch.traversalOrder(graph, 0));
    }

    @Test
    void hasPathReportsReachability() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertTrue(BreadthFirstSearch.hasPath(graph, 0, 5));
        assertTrue(BreadthFirstSearch.hasPath(graph, 3, 4));
        assertFalse(BreadthFirstSearch.hasPath(graph, 0, 6));
    }

    @Test
    void shortestPathFindsFewestEdgePath() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(0, 1, 3, 5), BreadthFirstSearch.shortestPath(graph, 0, 5));
        assertEquals(List.of(2, 4, 5), BreadthFirstSearch.shortestPath(graph, 2, 5));
    }

    @Test
    void shortestPathWorksWhenStartingFromDestinationSide() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(5, 3, 1, 0), BreadthFirstSearch.shortestPath(graph, 5, 0));
    }

    @Test
    void shortestPathReturnsEmptyWhenUnreachable() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(), BreadthFirstSearch.shortestPath(graph, 0, 6));
    }

    @Test
    void shortestPathStartEqualsTargetReturnsSingleVertex() {
        BreadthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(4), BreadthFirstSearch.shortestPath(graph, 4, 4));
    }

    private static BreadthFirstSearch.Graph sampleGraph() {
        BreadthFirstSearch.Graph graph = new BreadthFirstSearch.Graph(7);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(1, 4);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(3, 5);
        graph.addUndirectedEdge(4, 5);
        return graph;
    }
}
