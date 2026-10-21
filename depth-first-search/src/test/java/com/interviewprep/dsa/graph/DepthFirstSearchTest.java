package com.interviewprep.dsa.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class DepthFirstSearchTest {
    @Test
    void rejectsNegativeVertexCount() {
        assertThrows(IllegalArgumentException.class, () -> new DepthFirstSearch.Graph(-1));
    }

    @Test
    void startsWithEmptyAdjacencyLists() {
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(3);

        assertEquals(3, graph.vertexCount());
        assertEquals(List.of(), graph.neighbors(0));
        assertEquals(List.of(), graph.neighbors(1));
        assertEquals(List.of(), graph.neighbors(2));
    }

    @Test
    void rejectsInvalidVertices() {
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(3);

        assertThrows(IndexOutOfBoundsException.class, () -> graph.addUndirectedEdge(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.addUndirectedEdge(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.neighbors(3));
        assertThrows(IndexOutOfBoundsException.class, () -> DepthFirstSearch.traversalOrder(graph, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> DepthFirstSearch.hasPath(graph, 0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> DepthFirstSearch.pathTo(graph, 3, 0));
    }

    @Test
    void rejectsNullGraphForSearchMethods() {
        assertThrows(IllegalArgumentException.class, () -> DepthFirstSearch.traversalOrder(null, 0));
        assertThrows(IllegalArgumentException.class, () -> DepthFirstSearch.hasPath(null, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> DepthFirstSearch.pathTo(null, 0, 1));
    }

    @Test
    void addsUndirectedEdgesInBothDirections() {
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(4);

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
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(5);

        graph.addUndirectedEdge(0, 3);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 4);
        graph.addUndirectedEdge(0, 2);

        assertEquals(List.of(3, 1, 4, 2), graph.neighbors(0));
    }

    @Test
    void ignoresDuplicateEdgesAndStoresSelfLoopOnce() {
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(3);

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
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(3);
        graph.addUndirectedEdge(0, 1);

        List<Integer> neighbors = graph.neighbors(0);
        neighbors.add(2);

        assertEquals(List.of(1), graph.neighbors(0));
    }

    @Test
    void traversalVisitsInDepthFirstOrder() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(0, 1, 3, 5, 4, 2), DepthFirstSearch.traversalOrder(graph, 0));
    }

    @Test
    void traversalSkipsUnreachableVertices() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(6), DepthFirstSearch.traversalOrder(graph, 6));
        assertFalse(DepthFirstSearch.traversalOrder(graph, 0).contains(6));
    }

    @Test
    void traversalHandlesSingleVertexGraph() {
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(1);

        assertEquals(List.of(0), DepthFirstSearch.traversalOrder(graph, 0));
    }

    @Test
    void hasPathReportsReachability() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertTrue(DepthFirstSearch.hasPath(graph, 0, 5));
        assertTrue(DepthFirstSearch.hasPath(graph, 3, 4));
        assertFalse(DepthFirstSearch.hasPath(graph, 0, 6));
    }

    @Test
    void pathToFollowsDepthFirstPredecessors() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(0, 1, 3, 5, 4, 2), DepthFirstSearch.pathTo(graph, 0, 2));
        assertEquals(List.of(2, 0, 1, 3, 5), DepthFirstSearch.pathTo(graph, 2, 5));
    }

    @Test
    void pathToIsNotRequiredToBeShortest() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(0, 1, 3, 5, 4), DepthFirstSearch.pathTo(graph, 0, 4));
    }

    @Test
    void pathToReturnsEmptyWhenUnreachable() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(), DepthFirstSearch.pathTo(graph, 0, 6));
    }

    @Test
    void pathToStartEqualsTargetReturnsSingleVertex() {
        DepthFirstSearch.Graph graph = sampleGraph();

        assertEquals(List.of(4), DepthFirstSearch.pathTo(graph, 4, 4));
    }

    private static DepthFirstSearch.Graph sampleGraph() {
        DepthFirstSearch.Graph graph = new DepthFirstSearch.Graph(7);
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
